package Class;

public class User {
    protected int userId;
    protected String username;
    protected String password;
    protected String email;
    protected String role; // Tambahkan field role

    public User(int userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password; // Jika tidak diperlukan, bisa dihapus
        this.email = email;
        this.role = role; // Inisialisasi role
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role; // Kembalikan role
    }
}
