package home.work;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class ZipFileTests {

    private final ClassLoader classLoader = ZipFileTests.class.getClassLoader();

    @Test
    void csvFileTest() throws Exception {

        try (ZipInputStream zipInputStream = new ZipInputStream(Objects
                .requireNonNull(classLoader.getResourceAsStream("files.zip")))) {

            ZipEntry zipEntry;
            boolean csvFileIsFound = false;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                if (zipEntry.getName().contains(".csv")) {
                    csvFileIsFound = true;
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream));
                    List<String[]> data = csvReader.readAll();
                    assertEquals(101, data.size());
                    Assertions.assertArrayEquals(
                            new String[]{"Index", "Customer Id", "First Name", "Last Name", "Company", "City",
                                    "Country", "Phone 1", "Phone 2", "Email", "Subscription Date", "Website"},
                            data.getFirst()
                    );

                }
            }

            if (!csvFileIsFound) {
                Assertions.fail("The archive does not contain a csv file.");
            }
        }
    }

    @Test
    void pdfFileTest () throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(Objects
                .requireNonNull(classLoader.getResourceAsStream("files.zip")))) {
            ZipEntry zipEntry;
            boolean pdfIsFound = false;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".pdf")) {
                    pdfIsFound = true;
                    PDF pdf = new PDF(zipInputStream);
                    Assertions.assertEquals("Gene Brumblay", pdf.author);
                    Assertions.assertEquals("This is a test PDF document", pdf.title);
                }
            }

            if (!pdfIsFound) {
                Assertions.fail("The archive does not contain a pdf file.");
            }

        }
    }

    @Test
    void xlsFileTest() throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(Objects
                .requireNonNull(classLoader.getResourceAsStream("files.zip")))) {

            ZipEntry zipEntry;
            boolean xlsIsFound = false;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                if (zipEntry.getName().contains(".xls")) {
                    xlsIsFound = true;
                    XLS xls = new XLS(zipInputStream);

                    String expectedTitle = "First Name";
                    String actualTitle = xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
                    String expectedName = "Dulce";
                    String actualName = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();

                    Assertions.assertEquals(expectedTitle, actualTitle);
                    Assertions.assertEquals(expectedName, actualName);
                }

            }

            if (!xlsIsFound) {
                Assertions.fail("The archive does not contain a xls file.");
            }

        }
    }
}