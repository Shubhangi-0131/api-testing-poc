pre-requisites - download code and do setup from https://github.com/BestBuy/api-playground

Project has 2 sub-modules 

1) api-common - common code that all ApiClient classes can inherit from
2) api-testing-testng - copy of the tests under the main project "api-testing-poc" - this can be ignored for the moment
I'm using Java 15 but Java 11 should be fine.

You will need Lombok plug-in installed and configured on Eclipse /IntelliJ

I'm using Json2POJO Plugin in intelliJ to auto-generate classes from JSON request/response. 

There must either be inbuilt support or similar Plugin for Eclipse.

Other dependencies like Gradle should get auto-installed