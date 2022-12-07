import com.cloudbees.hudson.plugins.folder.*
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import jenkins.model.*

for (int i = 1; i <= 30; i++) {
    def name = sprintf("student%02d", i)
    createFolder(name)
}

def createFolder(def name) {
    String nameTemp = name + "-folder"

    def instance = Jenkins.getInstance()
    def folder = instance.getItem(name)

    if (folder == null) {
        folder = instance.createProject(Folder.class, name)
    } else {
        if (folder.getClass() != Folder.class) {
            folder = instance.createProject(Folder.class, nameTemp)
            
            Item[] items = instance.getItems(WorkflowJob.class)
            def job_regex = "^" + name

            items.grep { it.name =~ job_regex }.each { job ->
                Items.move(job, folder)
            }
            folder.renameTo(name)
        }
    }
}
