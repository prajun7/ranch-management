# Ranch Management

Our team developed the drone-enhanced ranch management software. The software was able to track livestock vitality and location, monitor feeding and watering systems, and monitor property status with the help of the drone(s).

Our team used Java 8 and Scenebuilder to develop the software. We created the Class diagrams, Sequence diagrams, Rapid prototypes, and other diagrams to assist us in developing the software. Also, different design patterns like Composite, Visitor, Adapter were used to develop the project.

Design Doc Link:
https://drive.google.com/file/d/1hOsAdkoVGHqz2agOgzKiSVzcpw8SjaFU/view?usp=sharing

Original Gitlab Link:
https://gitlab.com/cs420Group7/assignment03.git 

I reuploaded the files into my GitHub as our original files were in GitLab and were private. 

### Adding:
To begin adding items and containers, add a root by clicking on the '+ Add root' button, from there 
you will be able to add items to the root as well as containers, and items to those containers.

### Editing:
To edit a component, select it by clicking on it on the farm list on the left-hand side, 
then edit the attributes (name, dimensions, coordinates) on their respective text fields. To finalize click the 'Save' button.

### Deleting:
To delete a component, select it by clicking on it on the farm list, then click the 'Delete' button. 
Items can be deleted individually, and deleting a container will delete all the items within it.
You cannot delete the root.

### Drone & Command Center:
A Command Center can be added by clicking the '+ Add Command Center' button, and then a drone can be added by clicking the '+ Add Drone' button. The drone will be initially placed at the Command Center.
A Command Center must be present before a drone can be added.

### Drone Controls:
If a drone is connected, the 'Launch Drone' button will send the selected actions to the drone to perform,
while the 'Launch Simulator' will display a visual simulation of the actions being performed by the drone. 
The drone can be sent to visit a component by selecting the component from the farm list and clicking
the 'Visit item/Item Container' select button, then launching either the drone or a simulator by clicking on the respective action buttons ('Launch Simulator' & 'Launch Drone'). The drone will fly from the Command Center to the selected component. The drone can also be sent to scan the whole farm by clicking the 'Scan Farm' select button, then launching either the Drone or the simulation. The drone will perform a flight over the entire farm before returning to the Command Center.

*It is not an individual project. Thank you to all the team members for your contribution.*
