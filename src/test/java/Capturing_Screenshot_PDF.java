import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Capturing_Screenshot_PDF 
{
	@Test
	public void capturescreenshot_PDF() throws MalformedURLException, IOException, DocumentException
	{
		//System.setProperty("webdriver.chrome.driver","D://Chrome Driver 2.38//chromedriver_win32//chromedriver.exe");	
		System.setProperty("webdriver.chrome.driver", "E:\\Praveen_Automation\\Launching_Browsers\\Launching_Browsers_Latest\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.get("https://www.acegrades.com");

		// Capture screenshot and store it in byte[] array format
		byte[] input = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		Document document=new Document();
		String output = "E:\\ACEGRADES" + "Image" + ".pdf";
		
		FileOutputStream fos = new FileOutputStream(output);

		// Instantiate the PDF writer
		PdfWriter writer = PdfWriter.getInstance(document, fos);

		// open the pdf for writing
		writer.open();
		document.open();

		// process content into image
		Image im = Image.getInstance(input);

		//set the size of the image
		im.scaleToFit(PageSize.A4.getWidth()/2, PageSize.A4.getHeight()/2);

		// add the captured image to PDF
		document.add(im);
		document.add(new Paragraph(" "));

		//close the files and write to local system
		document.close();
		writer.close();
		
		driver.close();
	}
}
