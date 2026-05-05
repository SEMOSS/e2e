package aicore.utils;

import java.util.concurrent.locks.ReentrantLock;

public class AbstractDatabaseTestBase extends AbstractPlaywrightTestBase {

	private static final ReentrantLock TEST_DB_ENGINE_LOCK = new ReentrantLock();
	private static final ReentrantLock DIABETES_DB_ENGINE_LOCK = new ReentrantLock();
	
	
	
	protected void acquireTestDatabaseZipLock(Runnable action) {
		acquireDbLock(TEST_DB_ENGINE_LOCK, action);
	}
	
	protected void releaseTestDatabaseZipLock(Runnable action) {
		releaseDBLock(TEST_DB_ENGINE_LOCK, action);
	}

	protected void acquireDiabetesDatabaseZipLock(Runnable action) {
		acquireDbLock(DIABETES_DB_ENGINE_LOCK, action);
	}
	
	protected void releaseDiabetesDatabaseZipLock(Runnable action) {
		releaseDBLock(DIABETES_DB_ENGINE_LOCK, action);
	}
	
	/**
	 * Needed to lock out any threads looking to test any operation sthat requires the database 
	 * to be uploaded. This is to prevent failures that may occur when one resource modifies the 
	 * upload into a state that causes errors in another resource depending on the same upload
	 * @param action
	 */
	private void acquireDbLock(ReentrantLock lock, Runnable action) {
		lock.lock();
		try {
			action.run();
		} catch (Exception e) {
			lock.unlock();
			throw e;
		}
	}
	
	private void releaseDBLock(ReentrantLock lock, Runnable action) {
		try {
			action.run();
		} finally {
			// release lock so next thread can proceed
			lock.unlock();
		}
	}
}
