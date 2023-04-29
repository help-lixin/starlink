package help.lixin.svn.action;

import org.junit.Test;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import java.io.File;

public class SVNTest {

    static {
        SVNRepositoryFactoryImpl.setup();
    }

    @Test
    public void testCheckout() throws Exception {
        ISVNAuthenticationManager authenticationManager = BasicAuthenticationManager.newInstance("admin", "88888888".toCharArray());
        SVNURL url = SVNURL.parseURIEncoded("svn://192.168.8.16/spring_web_demo");
        SVNRepository repository = SVNRepositoryFactory.create(url, null);
        repository.setAuthenticationManager(authenticationManager);
        long latestRevision = repository.getLatestRevision();

        SVNClientManager clientManager = SVNClientManager.newInstance();
        clientManager.setAuthenticationManager(authenticationManager);

        SVNUpdateClient updateClient = clientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);

        File dest = new File("/tmp/test");
        SVNRevision svnRevision = SVNRevision.create(latestRevision);
        updateClient.doExport(repository.getLocation(), dest, svnRevision, svnRevision, null, true, SVNDepth.INFINITY);
    }
}
