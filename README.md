# CLOUDINARY API

## Overview
The API is useful to upload or Fetch Image files from Cloudinary Portal.

## Guidelines for Developer

1. Clone this Project

2. Create an Account in Cloudinary Portal.(https://cloudinary.com/)

3. After Account is created Get the Cloud Name, Key and Secret.

4. Replace the key in the below code of File : application.properties
```java
	cloud.name=
	cloud.api.key=
	cloud.api.secret=
```
5. Run the Application as Java Application.

## Guidelines for User

Upload:
1.To upload a file Hit the Application with below URL as per the below Snippet and it will be uploaded successfully.If there is any error.Error response will be sent accordingly.

```java
	/upload
```
