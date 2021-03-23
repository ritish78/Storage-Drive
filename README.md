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
![Upload successful](https://user-images.githubusercontent.com/36816476/108612463-1f300580-743d-11eb-8f25-11e0a9c2fbeb.PNG)

* Adding more files: NOTE: We should see the upload date of the File. That feature was added later.
![Adding more files](https://user-images.githubusercontent.com/36816476/108612480-3e2e9780-743d-11eb-9d6e-aa21e7cd1714.PNG)

* Cliking on `Download` button of the first file will download the file into the local machine:
![Clicking on download of the first file](https://user-images.githubusercontent.com/36816476/108612486-543c5800-743d-11eb-95b1-c28769e278d4.PNG)

* Clicking in `Delete` button of the first file will delete the file from database:
![Deleting the first file which we uploaded](https://user-images.githubusercontent.com/36816476/108612512-89e14100-743d-11eb-8b15-1bb18cbf8587.PNG)

* Adding file with the same file name in database will throw an error:
![Adding file with same file name will throw error](https://user-images.githubusercontent.com/36816476/108612525-a1b8c500-743d-11eb-822c-f377b3878787.PNG)

* Adding notes: 
![Adding notes](https://user-images.githubusercontent.com/36816476/108612530-af6e4a80-743d-11eb-87e5-599b5fd9b7ba.PNG)

* Adding more notes:
![Adding more notes](https://user-images.githubusercontent.com/36816476/108612535-be54fd00-743d-11eb-8bc1-e677b62decff.PNG)

* Adding credential for other website:
![Adding credential for another website](https://user-images.githubusercontent.com/36816476/108612540-d167cd00-743d-11eb-828a-8f182aeed764.PNG)

* Adding more `username/email` and `password`. In this page, we let the user only see the `salted` and `hashed` password:
![Adding more username and password](https://user-images.githubusercontent.com/36816476/108612547-e80e2400-743d-11eb-97f9-294aed4e33ca.PNG)

* By clicking on the `Edit` button you can view the password:
![By clicking on the Edit button you can see the password](https://user-images.githubusercontent.com/36816476/108612595-3d4a3580-743e-11eb-858d-eab283647a2b.PNG)

* Creating another user to test `authorization`.
![Creating another user](https://user-images.githubusercontent.com/36816476/108612607-5652e680-743e-11eb-9c9f-0ad323db175f.PNG)

* Empty `Home` page for new created user. One user can't view the files and other details of another user:
![One user can't view the files and other details of another user](https://user-images.githubusercontent.com/36816476/108612624-7b475980-743e-11eb-90ed-8b362bb6edc7.PNG)



  
