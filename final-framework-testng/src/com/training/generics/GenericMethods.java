
package com.training.generics;



import java.io.File;
import java.util.List;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;





/**

 * 

 * @author Naveen

 * @see this class will help when you want to do custom business logic, since  in POM we dont do 

 * 			dynamic elements available, when you want to iterate the table/accordion etc 

 * @since 17-Dec-2018 

 */

public class GenericMethods {

	WebDriver driver ; 

	

	public GenericMethods(WebDriver driver){

		this.driver = driver;

	}

	

	/**

	 * 

	 * @param locator 

	 * @param type

	 * @see type is id, name, xpath, text, partialtext

	 * @see locator will be the element to be found on DOM 

	 * @return  WebElement

	 * this method shall give provided it has single enty in the DOM

	 */

	public WebElement getElement(String locator, String type){

		WebElement element  = null;

		type = type.toLowerCase();

		

		if(type.equals("id")){

			element  =  driver.findElement(By.id(locator));

		} else if(type.equals("css")){

			element = driver.findElement(By.cssSelector(locator));

		}else if (type.equals("name")){

			element  = driver.findElement(By.name(locator));

		}else if(type.equals("xpath")){

			element = driver.findElement(By.xpath(locator));

		}

		if(checkSingleEntry(locator, type)){

			System.out.println("Element Found and Returned");

			return element;

		}	

		System.out.println("Sorry Element not found, so not returned...");

		return null;





	}

	

	

	// shall give if it has multiple entries as a list in DOM 

	

	public List<WebElement> getElementsAsList(String locator, String type){

		type = type.toLowerCase();

		if(type.equals("id")){

			return driver.findElements(By.id(locator));

		}else if(type.equals("name")){

			return driver.findElements(By.name(locator));

		}else if(type.equals("xpath")){

			return driver.findElements(By.xpath(locator));

		}else if(type.equals("class")){

			return driver.findElements(By.className(locator));

		}// other TODO 

		return null;

	}

	

	// return true if element exists 

	// this method works for us when we have more than 1 element 

	// to be found for 

	public boolean isElementFound(String locator, String type){

		return getElementsAsList(locator, type).size()>0;

	}

	

	// this method gives true only where there is an single entry 

	// in the DOM 

	public boolean checkSingleEntry(String locator, String type){

		return getElementsAsList(locator, type).size() ==1;

	}

	

	//Assert TextBox Value exists

	public boolean  validateTextBox(WebDriver driver,String type,String value,String ExpText)

	{

		boolean result= false;

		String actValue="";

		try

		{

			

			if(type.equalsIgnoreCase("id")){

				actValue= driver.findElement(By.id(value)).getAttribute("value");

			}else if(type.equalsIgnoreCase("name")){

				actValue= driver.findElement(By.name(value)).getAttribute("value");

			}else if(type.equalsIgnoreCase("xpath")){

				actValue= driver.findElement(By.xpath(value)).getAttribute("value");

			}else if(type.equalsIgnoreCase("class")){

				actValue= driver.findElement(By.className(value)).getAttribute("value");

			}// other TODO 

		

			if (actValue.equals(ExpText))

			{

				result=true;

			}

		}	

		catch (Exception e)

		{

			

		}

		return result;

					

	}	

	

	//Assert RadioButton is selected 

	

	public static boolean elementSelected(WebDriver driver,String xpath)

	{

		boolean result= false;

		try {

		 driver.findElement(By.xpath(xpath));

		 

		 result= true;

		}

		catch(Exception e)

		{

			System.out.println("element not found");

		}

		return result;

		

	}

	public static boolean getUrl(WebDriver driver,String ExpectedUrl)

	{

		boolean result= false;

	 if(driver.getTitle().equalsIgnoreCase(ExpectedUrl))

	 {

		result=true; 

	 }

		return result; 

	}

	 	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	

}