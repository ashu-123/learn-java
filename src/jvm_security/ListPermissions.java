package jvm_security;

import java.security.*;
import java.util.Enumeration;

public class ListPermissions {

    public static void main(String[] args) {
        new ListPermissions();
    }

    public ListPermissions(){
        System.setSecurityManager(new MySecurityManager());

        MySecurityManager securityManager = (MySecurityManager) System.getSecurityManager();

        securityManager.listPermissions();
    }

    class MySecurityManager extends SecurityManager {
        public void listPermissions () {
            Policy policy = Policy.getPolicy();
            Class[] classes =  getClassContext();

            for(Class cls : classes) {
                ProtectionDomain pd = cls.getProtectionDomain();
                CodeSource cs = pd.getCodeSource();

                PermissionCollection permissionCollection =  policy.getPermissions(cs);

                Enumeration<Permission> permissions = permissionCollection.elements();

                while(permissions.hasMoreElements()){
                    System.out.println(permissions.nextElement());
                }

            }
        }
    }
}
