# Obj File renderer

Parses and rasterize 3D object data from .obj files. Applies transformations and a lighting model to add 
lights and shadows to the scene.

OBJ format is a simple data-format that represents 3D geometry with triangles, using the position of each vertex,
the [UV position](https://en.wikipedia.org/wiki/UV_mapping) of each texture coordinate vertex, vertex normals and faces
that make each polygon.

Built for Computer Graphics, at Comenius University of Bratislava.

## ⁉️ Why?

The motivation of this project was learning how 3D modeled objects can be displayed in 2D
scenes and the different techniques that helps us achieve that objective.

## 📦 Technologies

The instructor of this class encouraged us to do this project in Python. However, I was a bit reluctant about it
because I am not much into it so for me it would be more complicated rather than easy.

As I love Rust and it is one of the programming languages that I want to really master, I considered
doing this project with it, but creating graphical interfaces with it was going to be a really hard problem which I
wasn't willing to cope with it in this project, as I had a deadline.

Finally, I decided to use **Kotlin**, as it was a language that I had been using for quite a bit given my Android
development journey. In addition, runs in the JVM, so it is very similar to Java, my most used language.
With Kotlin I was able to use [Java AWT](https://docs.oracle.com/javase/7/docs/api/java/awt/package-summary.html), a
library that I could use to create graphic interfaces with ease.

## ⚙️ Implementation

The functionality of this project can be divided in various steps:

1. Parse the obj file into the triangles that compose the object
2. Apply transformations to each point (translation, scaling and rotation)
3. Render each triangle of the object:
   1. Check if the triangle is not facing the camera (back-face culling)
   2. Calculate illumination model color (Blinn-Phong)
   3. Draw triangle with the calculated color
