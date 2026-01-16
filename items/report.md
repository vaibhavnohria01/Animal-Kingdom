# [G12 - Team Name] Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

Note that you should have removed ALL TEMPLATE/INSTRUCTION textes in your submission (like the current sentence), otherwise it hampers the professionality in your documentation.

*Here are some tips to write a good report:*

* `Bullet points` are allowed and strongly encouraged for this report. Try to summarise and list the highlights of your project (rather than give long paragraphs).*

* *Try to create `diagrams` for parts that could greatly benefit from it.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report. Note that they only provide part of the skeleton and your description should be more content-rich. Quick references about markdown by [CommonMark](https://commonmark.org/help/)*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: https://console.firebase.google.com/project/faunalencyclopedia/overview
   - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
   - Username: comp2100@anu.edu.au	Password: comp2100
   - Username: comp6442@anu.edu.au	Password: comp6442

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID   |  Name  |   Role |
|:------|:------:|-------:|
| u7630167 | Yihang Zhu | Key Author |
| [uid] | [name] | [role] |
| [uid] | [name] | [role] |
| [uid] | [name] | [role] |


## Summary of Individual Contributions

Specific details of individual contribution of each member to the project.

Each team member is responsible for writing **their own subsection**.

A generic summary will not be acceptable and may result in a significant lose of marks.

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*you should ALSO provide links to the specified classes and/or functions*
Note that the core criteria of contribution is based on `code contribution` (the technical developing of the App).

*Here is an example: (Note that you should remove the entire section (e.g. "others") if it is not applicable)*

1. **u7630167, Yihang Zhu**  I have 40% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Singleton Design Pattern - class DataHolder: [DataHolder.java](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/Data/DataHolder.java). The DataHolder class essentially is an AVL tree; in this app, all data are stored in a single tree, that is why I reckon that using the singleton design pattern to make sure each time the same tree will be called is an excellent idea. The idea of DataLoader-DataHolder comes from chatGPT. 
    - Feature DataFile - class DataLoader: [DataLoader.java](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/Data/DataLoader.java), [datalist.json](https://console.firebase.google.com/project/faunalencyclopedia/storage/faunalencyclopedia.appspot.com/files/~2Fdata). The data comes from [data.gov](data.gov), contains information of more than 10,000 species. Each item will be loaded and stored as an Animal object, and all objects are put in an AVL tree by id in default. There is another data file [history](https://console.firebase.google.com/project/faunalencyclopedia/firestore/data/~2Fhistory~2Fcomp6442@anu.edu.au) that is stored in Firestore which records items been browsed by each users.
    - Feature LoadShow Data: class LoginActivity: field [dataLoadingRunnable](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/LoginActivity.java#L40-71). It takes some time to fully load the data, which is unacceptable. Thus, the loading process has begin since the log in activity. Furthermore, I applied a handler and a runnable to accomplish the load show data part that we can browse the list before it has been fully loaded. Additionally, since the app loads data regularly, for each time we adjust the database, users can receive changes immediately. 
    - Feature FB-Auth: class LoginActivity: [LogoutState.java](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/LogoutState.java)(refactored by Vaibhav). Using Firebase Authentication to do the sign in and sign up part. This is more convenient than implementing on our own.
    - Feature FB-Persist: class HistoryActivity: [ContentActivity](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/ContentActivity.java#L70-88), [HistroyActivity.java](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/HistoryActivity.java). Each time a user chooses an item and go to the content page, the action will be recorded and sent to the firestore. Once they hit the "Histroy" button, the data from firestore will be loaded and showed. After hitting the "clear history" button, all data in firestore will be deleted and the list will be cleared immediately. Data of different users are separate.
    - Not in the list of features but an interesting part: AI content [The AI package](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/tree/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/AI). The introductory text of each item is generated by AI. With the help of an API it is not really hard to implement, but it looks really cool.
    - A part of the AVL tree: [AVLTree.java](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/java/com/g12/faunalencyclopedia/Search/AVLTree.java#107-144). Andy commpleted the AVL tree yet did not know how to collaborate with data, I did that.
    - Refactor code: I also improved code from others, with comments.<br><br>

  - **Code and App Design** 
    - As mentioned before, I implemented the singleton design pattern on the DataHolder class.
    - I designed the main part of the app, i.e. ListActivity [activity_list.xml](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/res/layout/activity_list.xml), ContentActivity [activity_content.xml](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/res/layout/activity_content.xml), and HistoryActivity [activity_history.xml](https://gitlab.cecs.anu.edu.au/u7630167/ga-23s2/-/blob/main/FaunalEncyclopedia/app/src/main/res/layout/activity_history.xml). I did not utilised tools despite Android Studio itself. Thus, the UI is rather rudimentary and dirty.<br><br>

  - **Others**: (only if significant and significantly different from an "average contribution") 
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* 
    - I am responsible for the firebase.<br><br>

2. **UID2, Name2**  I have xx% contribution, as follows: <br>
- **Code Contribution in the final App**
   - Feature A1, A2, A3 - class Dummy: [Dummy.java](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java)
    - XYZ Design Pattern -  class AnotherClass: [functionOne()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43), [function2()](the-URL)
    - ... (any other contribution in the code, including UI and data files) ... [Student class](../src/path/to/class/Student.java), ..., etc.*, [LanguageTranslator class](../src/path/to/class/LanguageTranslator.java): function1(), function2(), ... 
- **Code and App Design** 
   - [What design patterns, data structures, did the involved member propose?]*
   - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* 
- **Others**: (only if significant and significantly different from an "average contribution") 
    - I wrote the Application Description of the report.
    - I am responsible for the firebase.<br><br>


## Application Description

Faunal Encyclopedia is an application for biological savvies. Within such an application, users can search for species they want based on common names, scientific names, and so on. Besides basic information, there is also an introductory text about the species that is generated by AI.

### Application Use Cases and or Examples

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here is a pet training application example*

*Molly wants to inquiry about her cat, McPurr's recent troublesome behaviour*
1. *Molly notices that McPurr has been hostile since...*
2. *She makes a post about... with the tag...*
3. *Lachlan, a vet, writes a reply to Molly's post...*
4. ...
5. *Molly gives Lachlan's reply a 'tick' response*

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

<hr> 

### Application UML

![ClassDiagramExample](media/_examples/ClassDiagramExample.png) <br>
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

<hr>

## Code Design and Decisions

This is an important section of your report and should include all technical decisions made. Well-written justifications will increase your marks for both the report as well as for the relevant parts (e.g., data structure). This includes, for example,

- Details about the parser (describe the formal grammar and language used)

- Decisions made (e.g., explain why you chose one or another data structure, why you used a specific data model, etc.)

- Details about the design patterns used (where in the code, justification of the choice, etc)

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design.

<hr>

### Data Structures

*[What data structures did your team utilise? Where and why?]*

Here is a partial (short) example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * *It is more efficient than Arraylist for insertion with a time complexity O(1)*
      * *We don't need to access the item by index for xxx feature because...*
      * For the (part), the data ... (characteristics) ...

2. ...

3. ...

<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. *xxx Pattern*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * ...

<hr>

### Parser

### <u>Grammar(s)</u>
*[How do you design the grammar? What are the advantages of your designs?]*
*If there are several grammars, list them all under this section and what they relate to.*

Production Rules:

    <Non-Terminal> ::= <some output>
    <Non-Terminal> ::= <some output>


### <u>Tokenizers and Parsers</u>

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*

<hr>

### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

<br>
<hr>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Description of the feature ... (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of feature: ... <br>
   * Description of your implementation: ... <br>

2. [DataFiles]. Description  ... ... (...)
   * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
   * Link to the Firebase repo: ...

3. ...
   <br>

### Custom Features
Feature Category: Privacy <br>
1. [Privacy-Request]. Description of the feature  (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of your implementation: ... <br>
     <br>

2. [Privacy-Block]. Description ... ... (medium)
   ... ...
   <br><br>

Feature Category: Firebase Integration <br>
3. [FB-Auth] Description of the feature (easy)
   * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
   * Description of your implementation: ... <br>

<hr>

### Surprised Features

Suprised feature is not implemented

<br> <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
   - *A space bar (' ') in the sign in email will crash the application.*
   - ...

2. *Bug 2:*
3. ...

<br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

2. xxx

...

<br> <hr>


## Team Management

### Meetings Records
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each commited within 2 days aftre the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- *[Team Meeting 1](meeting1.md)*
- ...
- ...
- [Team Meeting 4](link_to_md_file.md)
- ... (Add any descriptions if needed) ...

<hr>

### Conflict Resolution Protocol
*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem?
(If you choose to make this an external document, link to it here)]*

This shall include an agreed procedure for situations including (but not limited to):
- e.g., if a member fails to meet the initial plan and/or deadlines
- e.g., if your group has issues, how will your group reach consensus or solve the problem?
- e.g., if a member gets sick, what is the solution? Alternatively, what is your plan to mitigate the impact of unforeseen incidents for this 6-to-8-week project? 
