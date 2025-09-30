# Use Case Diagram (UCD)

@startuml
-'skinparam monochrome true
-skinparam packageStyle rectangle
-'skinparam shadowing false
-
-skinparam rectangle{
-    BorderColor yellow
     -}
-
-'title Use Case Diagram Example
-
left to right direction

-rectangle "System Actors" as actors {
-    actor Freelancer
-    actor "Organization\nEmployee" as Employee
     -}
-
+actor "System Admin" as admin #green
+actor "unregistered user" as unUser #black
+actor Agent as Agent #blue
+actor Owner as Owner #red

-note right of Freelancer
-    Actor identified but still
-    no concrete use cases
     -end note
-
-rectangle "Use Cases" as usecases{
-    usecase "UC6 - Create a Task" as UC6
-    Employee --> UC6
     +rectangle "RealEstate USA App" #yellow{
+  usecase "US1 - Display Listed Properties" as UC1
+  usecase "US2 - Publish Announcement" as UC2
+  usecase "US3 - Register Employee" as UC3
+  usecase "US4 - Submit a request" as UC4
   }
   -note right of actors
-    Rectangles and Titles are for
-    demonstration purposes only.
-    Omitt on your diagrams.
     -end note
-
+unUser -- UC1
+Agent -- UC2
+admin -- UC3
+Owner -- UC4
+UC4--Agent
@enduml