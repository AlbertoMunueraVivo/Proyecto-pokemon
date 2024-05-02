package bbd;

public class SessionManager {
    public static void setInstance(SessionManager instance) {
		SessionManager.instance = instance;
	}

	private static SessionManager instance;
    private User currentUser;

    private SessionManager() { }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public int getCurrentUserId() {
        if (currentUser != null) {
            return currentUser.getId();
        } else {
            throw new IllegalStateException("No user is currently logged in.");
        }
    }
}


