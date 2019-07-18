import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Reader {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        StringMapper();
        FileMapper();
        writeFileMapper();
        JsonNestedReader();
    }

    private static void JsonNestedReader() throws IOException {
        StudentNested studentNested = mapper.readValue(
                new File("C:\\Users\\saicumbulam\\Documents\\Github\\JavaCookbook\\file\\sample-full.json"),
                StudentNested.class);
        System.out.println("Address " + studentNested.getAddress().getCity());
    }

    private static void writeFileMapper() throws IOException {
        Student student = mapper.readValue(new File("C:\\Users\\saicumbulam\\Documents\\Github\\JavaCookbook\\file\\student.txt"), Student.class);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("C:\\Users\\saicumbulam\\Documents\\Github\\JavaCookbook\\file\\studentout.txt"), student);
        System.out.println("Wrote file");
    }

    private static void FileMapper() throws IOException {
        Student student = mapper.readValue(new File("C:\\Users\\saicumbulam\\Documents\\Github\\JavaCookbook\\file\\student.txt"), Student.class);
        System.out.println("got it " + student.getName());
    }

    private static void StringMapper() {
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        //map json to student
        try {
            Student student = mapper.readValue(jsonString, Student.class);

            System.out.println(student);

            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);

            System.out.println(jsonString);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
