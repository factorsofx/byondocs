ByonDocs
========
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

Mission
---------
BYOND's native language, DM, suffers from a plethora of faults. One of the most notable is the complete lack of any tools for encapsulation, organization, or modularization. Any object type, any variable, or any procedure can be declared anywhere in the entire codebase. This makes it very difficult to navigate the codebase, especially for a beginner programmer, but even just for people who aren't used to the codebase.

Another significant problem BYOND/DM has is the lack of documentation tools. The closest offer by BYOND/DM is the object tree dump, which outputs in a confusing format and doesn't contain a lot of information.

ByonDocs seeks to solve the problems of navigability and documentation, at least partially. Its purpose is to generate a static webpage with details about every object node, its variables, its procedures, and its children stored in a consistent, easily readable format.

Documentation is supported by means of comments in the code. Take the following example:
~~~~
/**
 * Performs foo.
 */
 datum/bar/proc/foo()
	 // foo here
	 ..()
~~~~
This could be placed in any file across the codebase, in a place completely unrelated to `bar`. ByonDocs will consolidate all of these fragments into one place, and take comments like the helpful note in the example above and attach them to the relevant items.

--------------
Current State
----------------
In its current form, ByonDocs can parse the object tree and pick out the variables of object nodes and documentation comments. Procs are not yet parsed, and documentation comments are inserted into the document raw.

Goals
-------
- [ ] Parse documentation as markdown
- [ ] Include more navigability features in the generated HTML
- [ ] Include a feature to update existing documentation
- [ ] Inline links directly to source code

License
----------
ByonDocs is licensed under GPLv3 or later.