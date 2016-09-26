# Folder organization


- `libs` External java libs goes here.

- `data` Binary data goes here, it contains fonts, images, or other resources for the application.

- `java/procsynth/shine3` This is where the java code is. It furthermore subdivided :
	- `engine` is the block engine, it is written with re-usability in mind, it does not depend of an external lib.
	- `world` is where tools to define the real world lies.
	- `interface` is a processing applet that render the state of the engine (and the world ? (preferably no))
	- `shine` is where are defined the blocks to manipulate the __world__ using the __engine__.
- `javascript` Third party scripts or application scripts goes here.

## 