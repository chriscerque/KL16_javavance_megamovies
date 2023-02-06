package net.ent.etrs.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvUtils {
    private static List<Object> list = new ArrayList<>();//TODO

    public static void initFromCsv() {
        try {
            Path path = Paths.get("");
            if (Files.exists(path)) {
                System.out.println("File open");
                Files.lines(path).skip(1).limit(5).forEach(CsvUtils::createCategorie);
                list.stream().forEach(System.out::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createCategorie(final String s) {
        String[] line = s.split(";");
        Object c = null;//EntitiesFactory.; TODO
        list.add(c);
    }


}
