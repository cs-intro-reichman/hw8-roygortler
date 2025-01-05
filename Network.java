/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        for(int i=0;i<this.users.length;i++)
        {if(users[i]==null)
        return null;
        if(users[i].getName().toLowerCase().equals(name.toLowerCase()))
        return users[i];
        
    }return null;}

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
       if(this.getUser(name)==null&&this.userCount<this.users.length)
      {this.users[userCount]=new User(name);
        userCount++;
        return true ;}
        return false;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        if(name1==null||name2==null)
        return false;
        User u1=getUser(name1);
        User u2=getUser(name2);
        if (u1==null||u2==null||u1.getName()==u2.getName()) {
            return false;
        }
        
        return u1.addFollowee(name2);
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        int max=0,maxi=0;
        for(int i=0;i<users.length;i++){
            if(users[i]!=null&&!getUser(name).follows(users[i].getName())&&!name.equals(users[i].getName())&&getUser(name).countMutual(users[i])>max)
            {max=getUser(name).countMutual(users[i]);
            maxi=i;}
        }
        return users[maxi].getName();
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        
        int max=0,maxi=0;
        if(this.users[0]==null)
        return null;
        for(int i=0;i<users.length;i++){
            if(users[i]!=null)
            {if(followeeCount(users[i].getName())>max)
            {max=followeeCount(users[i].getName());
            maxi=i;}
        }}return users[maxi].getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int c=0;

        for(int j=0;j<users.length;j++){
            if(users[j]!=null)
          { if(!getUser(name).equals(users[j])&&users[j].follows(name))
           c++;
        }}return c;
       }
    

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       //// Replace the following statement with your code
       String ans="Network:";
       if(this.users[0]==null)
       return ans;
       for(int i=0;i<users.length;i++){
        if(users[i]!=null)
        ans=ans+"\n"+users[i].toString();
       
       }return ans;
    }
}
