Java-Crypto-Examples
v.0.1 (under construction)
====================

Some interesting examples of using crypto functionality (JCA/JCE, bcrypt, scrypt, other) in Java.

WARNING:

What we want to achieve with this project is to provide an overview over the crypto APIs in Java.
We want to talk about security, but there are certain exceptions how far we go.

1. The examples do not include checking any preconditions. Those checks are required in production
   code, but they would only obfuscate the examples making the basic idea harder to understand.
2. The examples do not provide any exception checking. All exceptions are just propagated higher.
   Again, this is a crucial and necessary feature in production code you will not find it in the
   examples we provide.

Our goal is not to provide a secure implementation. Our goal is:
- based on the examples explain, how a secure implementation could look like,
- identify and document the common errors and pitfalls people do (or might do) while using
  crypto in Java,
- give an overview over different possibilities of doing things in Java.

