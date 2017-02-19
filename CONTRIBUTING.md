# Javadoc

The Javadoc can be found [here](https://pr0csynth.github.io/Shine3/docs) or in the `docs` folder.

# Folder organization

- `libs` External java libs goes here.

- `data` Binary data goes here, it contains fonts, images, or other resources for the application.

- `java/procsynth/shine3` This is where the java code is. It furthermore subdivided :
	- `engine` is the block engine, it is written with re-usability in mind, it does not depend of an external lib.
	- `interface` is a processing applet that render the state of the engine, the block interfaces and other interfaces
	- `shine` is where are defined the specific blocks, blockfactory, interfaces that permit the manipulation of DMX, sequences, ...  
- `javascript` Third party scripts or application scripts goes here.

#