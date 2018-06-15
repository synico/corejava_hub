import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

public class GitTasks {
    
    private static final String CMD = "git version";
    
    ProcessBuilder pb = new ProcessBuilder(CMD);
    
    private void initialEnv() throws Exception {
        Map<String, String> env = pb.environment();
        String envPath = env.get("PATH");
        if(envPath != null) {
            envPath = envPath + ";C:/Users/907729/opt/git/bin";
            env.put("PATH", envPath);
        }
        pb.directory(new File("C:/Users/907729/scm/git/learn_hub/"));
        File log = new File("C:/Users/907729/git_task.log");
        pb.redirectErrorStream(true);
        pb.redirectOutput(Redirect.appendTo(log));
        Process p = pb.start();
        assert pb.redirectInput() == Redirect.PIPE;
        assert pb.redirectOutput().file() == log;
        assert p.getInputStream().read() == -1;
    }

    public static void main(String[] args) {
        GitTasks tasks = new GitTasks();
        try {
            tasks.initialEnv();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
