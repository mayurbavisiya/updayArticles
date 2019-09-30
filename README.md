1) This application is the REST APIs for articles including CRUD operation along with search it with different criteria.

2) Libraries

org.springframework.boot - 2.0.0.RELEASE
spring-boot-starter-data-jpa
spring-boot-starter-security

springfox-swagger-ui  (This for to provide GUI for api with the sample request, So User can directly access all the apis from browser )

com.h2database (To store data locally)

3) Test cases are there for some of the methods using Junit as well Mockito

4) Steps to run

	Checkout from https://github.com/mayurbavisiya/updayArticles.git
	
	Import in relavant IDE
	
	Run UpdayApplication.java file.
	
5) This application is deployed on Heroku cloud.In case if you do not wish to set up, You can directly access app from http:// 
 

Note:

API : /articles/showArticleByDate/{fromDate}/{toDate}
Need to use date format yyyy-MM-dd (i.e 2019-09-01) rest of the format will be consider as invalid and return 400(Bad request)

CRUD operation is carrying custom header with message in case of successfull operation. This code is there to simplify future scenario,In case of need to pass some information in header.

Spring security is enable, But curently does not created roles and users and will allow all the urls.(Set up is ready if in case of need them)


