package cn.iocoder.springboot.lab39.skywalkingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws URISyntaxException, IOException {
//        ProtectionDomain protectionDomain = Launcher.class.getProtectionDomain();
//        CodeSource codeSource = protectionDomain.getCodeSource();
//        URI location = (codeSource != null) ? codeSource.getLocation().toURI() : null;
//        String path = (location != null) ? location.getSchemeSpecificPart() : null;
//        if (path == null) {
//            throw new IllegalStateException("Unable to determine code source archive");
//        }
//        File root = new File(path);
//        System.out.println(root + "" + root.isDirectory());
//        // 创建 Archive 对象
//        Archive archive = new JarFileArchive(root);
//        // 创建 EntryFilter 对象
//        Archive.EntryFilter filter = new Archive.EntryFilter() {
//
//            static final String BOOT_INF_CLASSES = "BOOT-INF/classes/";
//
//            static final String BOOT_INF_LIB = "BOOT-INF/lib/";
//
//            @Override
//            public boolean matches(Archive.Entry entry) {
//                // 如果是目录的情况，只要 BOOT-INF/classes/ 目录
//                if (entry.isDirectory()) {
//                    return entry.getName().equals(BOOT_INF_CLASSES);
//                }
//                // 如果是文件的情况，只要 BOOT-INF/lib/ 目录下的 `jar` 包
//                return entry.getName().startsWith(BOOT_INF_LIB);
//            }
//
//        };
//        // 执行读取
//        for (Archive item : archive.getNestedArchives(filter)) {
//            System.out.println(item.getUrl());
//        }

        SpringApplication.run(Application.class, args);
    }

}
