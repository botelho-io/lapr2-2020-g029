# README

## General Info

This repository contains G29's LAPR2's final project.
This project was developed by:

* André de Sousa Botelho Ribeiro (1192232)
* Ricardo Filipe Mendes Moreira (1191631)

Here you will find:

* [The main domain model](docs/MD/MD.md)
* [The use case diagram](docs/DUC/DUC.md)
* [FURPS+](docs/FURPS.md)
* [Glossary](docs/Glossary.md)
* [Documentation on each use case](docs/README.md)
* [Documentation for the code in JavaDoc](docs/JavaDoc/index.html)
* [Unit Test Coverage Data](docs/UnitTests/index.html)
* [A Json Dump of the Team's Trello Board](docs/Trello/Board.json)

## How To Run This Project

The easiest and most haste-free way to run this project is with [*IntelliJ*](https://www.jetbrains.com/idea/download).

Once you have *IntelliJ* installed, open it, click *open project* and select the file `.idea/PFinal.iml`.

Once the project is open you can click with the left mouse button on the left-side pane to navigate to the file `PFinal/src/java/lapr/ui/Main` once there click the class `Main` with the right mouse button then click `Run 'Main.main()'`.

At this point, if you get a lot of red errors in the console, the easiest way to get rid of them is by downloading *JetBrain´s* own *JRE* called *JBR*, you can find it here: [JBR](https://confluence.jetbrains.com/pages/viewpage.action?pageId=157810879)

Once you have the *JBR* installed you will need to select in in `File > Project Structure ... > Project SDK`, make jure the *JBR* is selected on the dropdown.

Now you should be able to run the project.

You should also be able to run the unit test with coverage report by navigating to the folder `PFinal/src/test/java` clicking the folder with the right mouse button and clicking `Run 'All Tests' with Coverage`.

### Configuration File

In the same folder as this file there should be a configuration file called `config.properties`.

A sample configuration file would be:

```
admin.name = administrator
admin.email = admin@mail.com
admin.password = pswrd
api.email = lapr.api.defaults.EmailAPIAdapter
api.monetaryConversion = lapr.api.defaults.MonetaryConversionAPIAdapter
api.payment = lapr.api.defaults.PaymentAPIAdapter
api.passwordGenerator = lapr.api.defaults.PswGeneratorAPIAdapter
```

Where:

* `admin.name` sets the property of the administrator's name.
* `admin.email` sets the property of the administrator's email *(used to login)*
* `admin.password` sets the property of the administrator's password *(used to login)*
* `api.email` sets the email API.
* `api.monetaryConversion` sets the monetary conversion unit API.
* `api.payment` sets the bank payment API.
* `api.passwordGenerator` sets the password generator API.

Regarding the API's the word `defaults` may be changed to `sout` in order to get a debug API which logs all API calls to the standard output.