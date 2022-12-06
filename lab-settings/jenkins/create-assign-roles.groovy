import hudson.*
import hudson.model.*
import hudson.security.*
import jenkins.*
import jenkins.model.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import com.synopsys.arc.jenkins.plugins.rolestrategy.*
import java.lang.reflect.*

def globalAdminRole = "admin"
def globalReadRole = "student"

def access = [
  admins: ["penguin", "speaker01"],
  students: []
]

for (int i = 1; i <= 30; i++) {
  def student = sprintf("student%02d", i)
  access.students.add(student)
}

def adminPermissions = [
  "hudson.model.Hudson.Administer",
  "hudson.model.Hudson.Read"
]

def readPermissions = [
    "hudson.model.Hudson.Read",
    "hudson.model.Item.Discover",
    "hudson.model.Item.Read"
]

def studentPermissions = [
    "hudson.model.View.Configure",
    "hudson.model.View.Delete",
    "hudson.model.View.Read",
    "hudson.model.View.Create",
    "hudson.model.Item.Configure",
    "hudson.model.Item.Build",
    "hudson.model.Item.Create",
    "hudson.model.Item.Cancel",
    "hudson.model.Item.Read",
    "hudson.model.Item.Discover",
    "hudson.model.Item.Move",
    "hudson.model.Item.Workspace",
    "hudson.model.Item.Delete",
    "com.cloudbees.plugins.credentials.CredentialsProvider.View",
    "com.cloudbees.plugins.credentials.CredentialsProvider.Update",
    "com.cloudbees.plugins.credentials.CredentialsProvider.Delete",
    "com.cloudbees.plugins.credentials.CredentialsProvider.Create",
    "hudson.model.Run.Delete",
    "hudson.model.Run.Update",
    "hudson.scm.SCM.Tag"
]

RoleBasedAuthorizationStrategy roleBasedAuthenticationStrategy = new RoleBasedAuthorizationStrategy()
Jenkins.instance.setAuthorizationStrategy(roleBasedAuthenticationStrategy)

Constructor[] constrs = Role.class.getConstructors();
for (Constructor<?> c : constrs) {
  c.setAccessible(true);
}

Method assignRoleMethod = RoleBasedAuthorizationStrategy.class.getDeclaredMethod("assignRole", RoleType.class, Role.class, String.class);
assignRoleMethod.setAccessible(true);
println("HACK! changing visibility of RoleBasedAuthorizationStrategy.assignRole")

Set<Permission> adminPermissionSet = new HashSet<Permission>();
adminPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    adminPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> readPermissionSet = new HashSet<Permission>();
readPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    readPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> studentPermissionSet = new HashSet<Permission>();
studentPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    studentPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Role adminRole = new Role(globalAdminRole, adminPermissionSet);
roleBasedAuthenticationStrategy.addRole(RoleType.Global, adminRole);

Role readRole = new Role(globalReadRole, readPermissionSet);
roleBasedAuthenticationStrategy.addRole(RoleType.Global, readRole);

access.admins.each { admin ->
  println("Granting admin role to ${admin}")
  roleBasedAuthenticationStrategy.assignRole(RoleType.Global, adminRole, admin);  
}

access.students.each { student ->
  println("Granting read role to ${student}")
  roleBasedAuthenticationStrategy.assignRole(RoleType.Global, readRole, student); 

  def pttrn = sprintf("%s*", student) 
  Role studentRole = new Role(student, pttrn, studentPermissionSet);
  roleBasedAuthenticationStrategy.addRole(RoleType.Project, studentRole);

  // Assign the role
  println "Granting ${student}/${pttrn} role to ${student}"
  roleBasedAuthenticationStrategy.assignRole(RoleType.Project, studentRole, student);
}

Jenkins.instance.save()