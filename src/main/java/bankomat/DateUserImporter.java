package bankomat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DateUserImporter {
    private final String filePath;

    public DateUserImporter(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> readUser(){
        return fileAsLine()
                .stream()
                .map(str -> str.split(","))
                .collect(Collectors.toList());
    }

    private List<String> fileAsLine(){
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
            log.error(filePath + " - File path is not found");
            return List.of();
        }
    }
}
