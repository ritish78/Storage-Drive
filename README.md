# Storage-Drive
A web application to upload files, notes and store username and password for different websites. This project is under development. Selenium Webdriver will be added. 
* This web app is deployed to heroku: https://storage-drive.herokuapp.com
  * Initial load time will be more than 10 seconds as it takes time for heroku to start the web app. Heroku stops the app if we don't use the web app for more than half hour to save server time. 
  * To test the file upload, please select the file of least size. Its best if we create a new text document with only few bytes of size.


# How to install:
1. Using terminal clone this repo by this command:
````
git clone https://github.com/ritish78/Storage-Drive.git
````

2. Once the files is in your system, you can open it in IDE of your choice with `Maven`.
3. In the terminal of IDE, type:
````
mvn clean install
````
4. Then, you can start the `StorageDriveApplication.java` or in terminal you can type:
````
mvn spring-boot:run
````
5. Then the embedded server `Tomcat`, will start the application on port `9000`.

# End Points available:
* For `User`: 
  * `GET` /home
  * `GET` /login
  * `GET` /signup
  * `POST` /signup
* For `File`:
  * `POST` /file/upload
  * `GET` /file/delete
  * `GET` /file/download
* For `Note`:
  * `POST` /note/new
  * `GET` /note/delete
  * `GET` /note
* For `Credential`:
  * `POST` /credential/new
  *  `GET` /credential/delete
  *  `GET` /credential

# Class Diagram:
![data diagram 2nd](https://user-images.githubusercontent.com/36816476/108646490-2b35c900-750a-11eb-9bae-a0767bec02a0.PNG)

  
# Usage:
* Going to `http://localhost:9000` in web browser. We get the `Login` page as default page.
  * This app is also deployed to heroku. You can check it out without cloning this repo.
 ![login screen](https://user-images.githubusercontent.com/36816476/112091395-5f73d680-8be9-11eb-9fa3-535f7147667d.PNG)

* Filling details in `Sign Up` page:
![Filling details in Sign Up screen](https://user-images.githubusercontent.com/36816476/108612359-40442680-743c-11eb-8428-b12289d142b1.PNG)

* We signed up successfully it redirects to `login` page and then filling the details in `login` page:
![after signing up](https://user-images.githubusercontent.com/36816476/112091427-71ee1000-8be9-11eb-9946-4226bc497e04.PNG)

* `Home` page after we sign in with correct credentials:
![Home page after entering correct details](https://user-images.githubusercontent.com/36816476/112091479-96e28300-8be9-11eb-8420-b328e7a2c0c5.PNG)


* We can't get to the `Home` page if we provide incorrect details:
![Incorrect details](https://user-images.githubusercontent.com/36816476/112091530-b083ca80-8be9-11eb-8297-1983e89740f9.PNG)

* Adding first file:
![Adding first file](https://user-images.githubusercontent.com/36816476/108612453-045d9100-743d-11eb-8890-0b0e8786b748.PNG)

* We get this screen if the file upload is successful:
![File Upload successfull](https://user-images.githubusercontent.com/36816476/112092198-276d9300-8beb-11eb-8b39-24c1ec528ae4.PNG)

* Adding more files: NOTE: We should see the upload date of the File. That feature was added later.
![Adding more files](https://user-images.githubusercontent.com/36816476/112092204-2b011a00-8beb-11eb-872e-cd07a4c21690.PNG)

* Cliking on `Download` button of the first file will download the file into the local machine:
![Clicking on download button](https://user-images.githubusercontent.com/36816476/112092209-2d637400-8beb-11eb-9a1d-fe317040173f.PNG)

* Clicking in `Delete` button of the first file will delete the file from database:
![Clicking on delete button of file](https://user-images.githubusercontent.com/36816476/112092215-2fc5ce00-8beb-11eb-8613-339e04cf8192.PNG)

* Adding file with the same file name in database will throw an error:
![Adding file with same file name in database](https://user-images.githubusercontent.com/36816476/112092223-32282800-8beb-11eb-8393-7425bd81eae0.PNG)

* Adding notes: 
![Adding notes](https://user-images.githubusercontent.com/36816476/112092229-35231880-8beb-11eb-8ee8-06914348c165.PNG)

* Adding more notes:
![Adding more notes](https://user-images.githubusercontent.com/36816476/112092235-381e0900-8beb-11eb-9108-e86353f73c80.PNG)

* Adding credential for other website:
![Adding credential of other website](https://user-images.githubusercontent.com/36816476/112092239-3a806300-8beb-11eb-8310-3215037429d9.PNG)

* Adding more `username/email` and `password`. In this page, we let the user only see the `salted` and `hashed` password:
![Adding more credential](https://user-images.githubusercontent.com/36816476/112092247-3d7b5380-8beb-11eb-9d50-cf2bef4a3874.PNG)

* By clicking on the `Edit` button you can view the password:
![Clicking on Edit button of credential](https://user-images.githubusercontent.com/36816476/112092253-3fddad80-8beb-11eb-89b4-1b20b0c30be6.PNG)

* Creating another user to test `authorization`.
![Another user](https://user-images.githubusercontent.com/36816476/112092256-41a77100-8beb-11eb-8600-c388c1da1359.PNG)

* Empty `Home` page for new created user. One user can't view the files and other details of another user:
![another user home screen](https://user-images.githubusercontent.com/36816476/112092666-17a27e80-8bec-11eb-9f38-25a51b386f4b.PNG)



  
