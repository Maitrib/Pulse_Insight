

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

/**
 *
 * CommandLine is a helper class which allows executing bash
 * commands while the internal workings of Groovy are hidden
 * away from the user.
 *
 */
public class CommandLine {


	/**
	 * Executes the command specified by <code>cmd</code> in bash, handling errors
	 * internally and returning standard outputs.
	 * <p>
	 * Internally it updates the PATH environment variable to ensure any
	 * binary stored in /usr/local/bin can also be executed
	 *
	 * @param cmd  				Command string to be executed in bash
	 * @param cwd				Working directory if given the command will be executed	inside this directory
	 * @return      		 	[String stdout, String stderr] from executing <code>cmd</code>
	 * @throws AssertionError 	If bash fails to execute <code>cmd</code>
	 */
//	@Keyword
//	def static run(String cmd, File cwd = null){
//
//		// update environment with the path to the local binaries
//		def env = System.getenv()
//		def envArray = []
//
//		for (String key : env.keySet()) {
//			if(key != "PATH"){
//				envArray.add(key + "=" + env.get(key));
//			}
//			else{
//				/*
//				 * forcing Katalon to use all binaries on Macs as default path is
//				 * PATH:/usr/bin:/bin:/usr/sbin:/sbin irrespective of system PATH variable
//				 */
//				envArray.add(key + "=" + env.get(key) + ":/usr/local/bin");
//			}
//		}
//
//
//		// executing with the new environment and null for directory to use $pwd
//		def sout = new StringBuilder()
//		def serr = new StringBuilder()
//		// ensure it uses the bash console
//		KeywordUtil.logInfo("Running shell command: $cmd")
//
//		// allows running on both jenkins agent and locally
//		def proc =  ["bash", "-c", cmd].execute((String[])envArray, cwd);
//
//		proc.consumeProcessOutput(sout, serr)
//		proc.waitFor();
//		KeywordUtil.logInfo("[STDERR]: " + serr.toString())
//		KeywordUtil.logInfo("[STDOUT]: " + sout.toString())
//
//		// exit if there was an execution error
//		assert !proc.exitValue(), "[ERROR] " + serr.toString();
//		return [
//			sout.toString(),
//			serr.toString()
//		]
//	}
//
//
//	/** 
//	 * Update <code>/etc/hosts</code> with entries for the domains unitydev.io
//	 * and <code>tenantSubdomain</code> to be resolved via <code>vtmIp</code>
//	 * <p>
//	 * Internally calls the {@link utils.CommandLine#run} to execute the corresponding Python 
//	 * script and handles execution error
//	 * 
//	 * TODO: once DNS is fixed, this method and underlying script should be removed
//	 *
//	 * @param 	vtmIp  	 		IP address on which VTM ingress is accessible
//	 * @param 	tenantSubdomain Name of subdomain of the tenant in  he unitydev.io domain
//	 * @return  			 	[String stdout, String stderr] from executing the python script
//	 */
//	@Keyword
//	def static updateEtcHosts(String vtmIp, String tenantSubdomain = null){
//
//		// empty argument will cause issues for the script
//		String subdomainArgument = (tenantSubdomain != null) ? "--tenant-subdomain $tenantSubdomain" : "";
//		return run("./update_etc_hosts.py --vtm-ip $vtmIp $subdomainArgument")
//	}
//
//	/** 
//	 * Destroy the VM <code>vMName</code> from from the specified vSphere host <code>vSphereHost</code> using
//	 * credentials  <code>username</code> and <code>password</code>
//	 * <p>
//	 * Internally calls the {@link utils.CommandLine#run} to execute the corresponding Python 
//	 * script and handles execution error
//	 *
//	 * @param 	vMName  	 	Name of the VM to be deleted
//	 * @param 	vSphereHost  	VSphere host it is to be deleted from
//	 * @param 	username  		Username with permission to delete VM's from the <code>vSphereHost</code>
//	 * @param 	password 		Password accompanying the  <code>username</code>
//	 * @return  				[String stdout, String stderr] from executing the underlying Go script
//	 */
//	@Keyword
//	def static destroyVM(String vMName, String vSphereHost, String username, String password){
//		// using =true to ensure no ambiguity is there that all args after are VM names to be deleted
//		return run("./govc_linux_amd64 vm.destroy -u $username:$password@$vSphereHost -k=true $vMName")
//	}
//
//
//

}