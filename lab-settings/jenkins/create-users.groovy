import jenkins.model.*
import hudson.security.*

for (int i = 1; i <= 30; i++) {
    def student = sprintf("student%02d\n", i)
    def instance = Jenkins.getInstance()
    def hudsonRealm = new HudsonPrivateSecurityRealm(false)

    hudsonRealm.createAccount(student, student)
    instance.setSecurityRealm(hudsonRealm)
    instance.save()
}