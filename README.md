## FenixEdu SDK for Java

The FenixEdu SDK for Java allows you to easily invoke the API endpoints, abstracting the oAuth protocol.

### Usage

You should make available in your classpath, a file named ```fenixedu.config``` and include the following configuration:

	fenixedu.consumer.key=<your-fenixedu-consumer-key>
	fenixedu.consumer.secret=<your-fenixedu-consumer-key>
	
	fenixedu.base.url=https://fenix.ist.utl.pt
	fenixedu.callback.url=<your-callback-url>

Then, to use the API, you should include the following snippet of code:

	FenixEduClient client = FenixEduClientFactory.getSingleton();
	String authUrl = client.getAuthenticationUrl();

Redirect the user to the ```authUrl``` and if the user accepts, the url specified in the ```fenixedu.callback.url``` will be called with a query param named ```code``` that you should use and set:
	
	FenixEduClient client = FenixEduClientFactory.getSingleton();
	client.setCode(code);

	//After you set the code, you can access the ```accessToken```
	//that you should store for future requests in behalf of the user
	//that authorized your application.
	String accessToken = client.getConfig().getAccessToken();
	
	//And you can invoke the API (e.g. getPerson endpoint)
	JsonObject jsonObject = client.getPerson();
	String istId = jsonObject.get("istId").getAsString();


### Maven Projects

If you use Maven to build your Java project, you can easily use this SDK by including the following dependency:

	<dependency>
		<groupId>pt.ist</groupId>
		<artifactId>fenixedu-sdk</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>

Remember to also update your project repositories, and include our Nexus repository:

	<repository>
		<id>fenix-ashes-maven-repository</id>
		<url>https://fenix-ashes.ist.utl.pt/nexus/content/groups/fenix-ashes-maven-repository</url>
	</repository>

### Other Project Builders

If you use other tool than Maven to build your project, here's the dependency tree for the SDK: 

	pt.ist:fenixedu-sdk:jar:0.0.1-SNAPSHOT
	+- com.sun.jersey:jersey-client:jar:1.17.1:compile
	|  \- com.sun.jersey:jersey-core:jar:1.17.1:compile
	+- com.google.guava:guava:jar:14.0.1:compile
	\- com.google.code.gson:gson:jar:2.2.4:compile
