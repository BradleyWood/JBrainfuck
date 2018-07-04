# JBrainfuck

A Brainfuck interpreter for the jvm built using Antlr.


# Setup

```
git clone https://github.com/BradleyWood/JBrainfuck.git
```

```
mvn install
```

# Running


```
java -jar jbrainfuck-1.0-SNAPSHOT-jar-with-dependencies.jar <brainfuck-src-file.b>
```

# About Brainfuck

Brainfuck is a minimalist esoteric programming language with just
8 commands. It is designed to challenge and amuse programmers.


| Command:  | Meaning |
| :------: | :------ |
| >        | Increment the data pointer|
| <        | Decrement the data pointer|
| +        | Increment the byte at the data pointer|
| -        | Decrement the byte at the data pointer|
| .        | Output the byte at the data pointer|
| ,        | Read and store a byte at the data pointer|
| [        | If the byte at the data pointer is zero skip to next matching']' |
| ]        | If the byte at the data pointer is not zero, jump to the previous matching '['|
