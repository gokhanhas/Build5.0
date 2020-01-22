package BuilSocketServer;

//Directory değiştirilecek

import org.apache.maven.shared.invoker.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
//import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class Maven_Builder {

    private String url;
    private String directory; // change project name
    private Git git;
    private RevCommit commit;
    private String username;
    private String password;
    private String forBuildPath;
    


    public Maven_Builder(String target_url , String direc , String username_ , String pass,String forBuild){
        this.url = target_url;
        this.directory = direc;
        this.username = username_;
        this.password = pass;
        this.forBuildPath = forBuild;
    }

    
    public void build() throws Exception {

    	System.err.print("BUILD STARTED\n");
        Runtime.getRuntime().exec("rm -rf /tmp/" + "GtuDevOps" ); //remove directory
        TimeUnit.SECONDS.sleep(1);
        // 1-clone
        
    	/*
        System.err.print("MAVEN BUILDER : " + url + "  *****\n");
        System.err.print("directory : " + directory + "  *****\n");
        System.err.print("Where is POM file : " + "/tmp/" + directory +"/" + directory +"/"+ forBuildPath + "/pom.xml" + "  *****\n");
        */
        
        git = Git.cloneRepository()
                .setURI( url )
                .setDirectory(new File("/tmp/" + "GtuDevOps" ))
                .call();

        InvocationRequest request = new DefaultInvocationRequest();

        request.setPomFile( new File( "/tmp/" + "GtuDevOps"  +"/" + directory +"/"+ forBuildPath + "/pom.xml" ) ); //set pom file to run

        request.setGoals( Collections.singletonList( "clean" ) ); //set clean command
        // request.setGoals( Collections.singletonList( "compile" ) ); //set build command
         request.setGoals( Collections.singletonList( "package" ) ); //set jar file command
        // request.setGoals(Collections.singletonList("-Dmaven.test.skip=true"));
        
         Properties properties = new Properties();
         properties.setProperty("skipTests", "true");
         request.setProperties(properties);

      
        Invoker invoker = new DefaultInvoker();
      
        
		String M2_HOME = System.getenv("M2_HOME");

		if (M2_HOME == null)
			M2_HOME = "/usr/share/maven";
		
		
        invoker.setMavenHome(new File(M2_HOME)); //need to set the maven home
        TimeUnit.SECONDS.sleep(1);
        invoker.execute( request ); //build the project
        
    
    }

    /**
	 * 
	 * @param signal
	 * 
	 * 
     * @throws NoFilepatternException 
     * @throws WrongRepositoryStateException 
     * @throws JGitInternalException 
     * @throws ConcurrentRefUpdateException 
     * @throws NoMessageException 
     * @throws NoHeadException 
     * @throws InvalidRemoteException 
     * @throws IOException 
     * @throws InterruptedException 
	 */
    public void push(Boolean signal) throws NoFilepatternException,
    			NoHeadException, NoMessageException, ConcurrentRefUpdateException, JGitInternalException,
    			WrongRepositoryStateException, InvalidRemoteException, IOException, InterruptedException 
    {

        if(signal){

            // add
        	// DirCache index = git.add().addFilepattern( "." ).call();
        	// System.err.println(System.getProperty("user.dir"));
        	// String cwd = System.getProperty("user.dir");
        	
        	// System.setProperty("user.dir", "/tmp/" + "GtuDevOps" + "/" + directory);

        	//System.err.println("push func");

        	// git.add().addFilepattern(  "." ).call();
            // commit
            // commit = git.commit().setMessage( "Grup-1, Build Success!" ).call();
            // push to remote:
        	// PushCommand pushCommand = git.push();
        	// pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));

        	// pushCommand.call();

        	// System.err.println("GİT BASLANGİCİ");

        	
        	Runtime.getRuntime().exec("git -C /tmp/GtuDevOps/ remote set-url origin https://" + username + ":" + password + "@github.com/" + username + "/GtuDevOps.git");
        	// InputStream gitStream = Runtime.getRuntime().exec("git -C /home/bilmuhlab/DevOps_Project/Workspace/Dev/GtuDevOps/" + directory + "/SumNumbers-master add -f target").getInputStream();

             //try {
                String line;
                TimeUnit.SECONDS.sleep(1);
                Runtime.getRuntime().exec("git -C /tmp/GtuDevOps/" + directory + "/"+ forBuildPath + " add -f .");
                TimeUnit.SECONDS.sleep(2);
                Process p = Runtime.getRuntime().exec("git -C /tmp/GtuDevOps/" + directory + "/"+ forBuildPath +" commit -m \"Build\" ");
                TimeUnit.SECONDS.sleep(2);
                Runtime.getRuntime().exec("git -C /tmp/GtuDevOps/" + directory + "/" + forBuildPath  + " push -f -u origin master");
                TimeUnit.SECONDS.sleep(2);
                /*
                BufferedReader err = new BufferedReader(
                        new InputStreamReader(p.getErrorStream()) );
                while ((line = err.readLine()) != null) {
                  System.err.println(line);
                }
                err.close();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(p.getInputStream()) );
                while ((line = in.readLine()) != null) {
                  System.err.println(line);
                }
                in.close();
              }
              catch (Exception e) {
            	  System.err.println("add excepiton");
              }
              */
        	
        	
        	// System.err.println("son");
        	/*
        	String gitPy = "/home/bilmuhlab/DevOps_Project/Workspace/Dev/src/main/java/BuilSocketServer/GitPush.py";
        	String pushCommend = "python3 "+ gitPy+ " " + username + " " + password + " " + url;
        	
        	
        	Runtime.getRuntime().exec( pushCommend ); 
        	*/
        	// System.setProperty("user.dir", cwd);
        	// System.err.println(System.getProperty("user.dir"));

        }

        Runtime.getRuntime().exec("rm -rf /tmp/" + "GtuDevOps" ); //remove directory
    }

    /**
	 * 
	 * @return
	 */
    public String getCommitID(){

        String commitId = commit.getId().toString();
        String writeToJson = commitId.split(" ")[1];

        return writeToJson;
    }
}