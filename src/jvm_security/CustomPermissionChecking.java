package jvm_security;

public class CustomPermissionChecking {

    public static void main(String[] args) {
        CustomPermissionChecking customPermissionChecking = new CustomPermissionChecking();
        customPermissionChecking.makeItSo(8);
    }

    private void makeItSo(int warp) {
        SecurityManager securityManager = System.getSecurityManager();
        if(securityManager != null){
            securityManager.checkPermission(new WarpPermission("maxWarp", String.valueOf(warp)));
        }

        System.out.println("Warp now set to: " + warp);
    }

}
