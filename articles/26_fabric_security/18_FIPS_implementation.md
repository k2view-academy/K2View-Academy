# FIPS Overview

FIPS (Federal Information Processing Standards) is a set of standards that defines encryption algorithms and other digital technology processes for use within non-military federal government agencies and by any government contractors working with these agencies. 

In particular, FIPS 140-2 (Federal Information Processing Standard 140-2) is a security accreditation program for validating that the cryptographic modules produced by private sector companies meet well-defined security standards.
[FIPS PUB 140-2](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.140-2.pdf) provides the full list of instructions and architecture documents needed to meet the Cryptographic Modules Security requirements.


## Fabric and FIPS

K2View has integrated the [Bouncycastle](https://www.bouncycastle.org/index.html) Java stack to ensure FIPS-compliance for all [Fabric cryptographic algorithms](/articles/26_fabric_security/03_fabric_LUI_encryption.md).

By default, Fabric boots up with FIPS mode set to **off**. 
Yet, even in this mode, Fabric only uses FIPS-compliant protocols and standards implementions with Fabric cryptographic embedded algorithms.


### FIPS Verification Command

Fabric's command ```version fips``` returns the FIPS version currently used and the status of all the cryptographics components included in the system. All java jars are checked validated or rejected    


### FIPS with mode set to **ON**

To switch on FIPS mode, do the following steps:
- Go to the **$K2_HOME/config/** directory,
- Open the **modules.ini** file
- Modify the fips entry to reflect the following value: ```fips:mode=on```

Fabric's server needs to be restarted for this change to be validated. 
This can be achieved by clicking on the server start/stop button on the top-left menu of the Fabric Studio. 

Once re-started, start a Fabric Console CLI window and execute the following command: 

```fabric>version fips;```

```
|Check                        |Result                                                          |Status|
+-----------------------------+----------------------------------------------------------------+------+
|FIPS Mode                    |on                                                              |passed|
|FIPS Ready                   |140-2                                                           |passed|
|info                         |Bouncy Castle FIPS Java API 1.0.2.1                             |passed|
|info                         |CMVP Certificate #3514                                          |passed|
|HMAC                         |d68017e6f45512a51a862c77e4fa2348d0d24d816bc3f76fcf30375052216e86|passed|
|AES/CBC/PKCS5Padding Explicit|BCFIPS                                                          |passed|
|AES/CBC/PKCS5Padding Implicit|BCFIPS                                                          |passed|
|SHA512_256Secure             |BCFIPS                                                          |passed|
|SHA512Secure                 |BCFIPS                                                          |passed|
|SHA256Secure                 |BCFIPS                                                          |passed|
|SHA512_256                   |BCFIPS                                                          |passed|
|SHA512                       |BCFIPS                                                          |passed|
|SHA256                       |BCFIPS                                                          |passed|
|SHA1                         |BCFIPS                                                          |passed|
|MD5                          |BCFIPS                                                          |passed|
|STRONG_AES256                |BCFIPS                                                          |passed|
|PLAIN_AES256                 |BCFIPS                                                          |passed|
|PLAIN_AES128                 |BCFIPS                                                          |passed|
|SSL Context                  |BCJSSE                                                          |passed|
|SecureRandom                 |BCFIPS                                                          |passed|

 
(20 rows)
```

Note that:
- BCFIPS means that the algorithm is provided via the jar file bc-fips-1.0.1.jar
- BCJSSE means that the Java Secure Socket Extension (JSSE) from Bouncy Castle is used.

### FIPS with mode set to **STRICT**

To switch on FIPS mode, do the following steps:
- Go to the **$K2_HOME/config/** directory,
- Open the **modules.ini** file
- Modify the fips entry to reflect the following value: ```fips:mode=strict```

Fabric needs to be restarted for this change to be validated. This can be achieved by clicking on the server start/stop button on the top-left menu of the Fabric Studio. 

Once re-started, start a Fabric Console CLI window and execute the following command: 


```fabric>version fips;```

```
|Check                        |Result                                                          |Status|
+-----------------------------+----------------------------------------------------------------+------+
|FIPS Mode                    |strict                                                          |passed|
|FIPS Ready                   |140-2                                                           |passed|
|info                         |Bouncy Castle FIPS Java API 1.0.2.1                             |passed|
|info                         |CMVP Certificate #3514                                          |passed|
|FIPS Only Algorithms         |true                                                            |passed|
|TLS FIPS only mode           |true                                                            |passed|
|HMAC                         |d68017e6f45512a51a862c77e4fa2348d0d24d816bc3f76fcf30375052216e86|passed|
|SunJCE                       |Not present                                                     |passed|
|AES/CBC/PKCS5Padding Explicit|BCFIPS                                                          |passed|
|AES/CBC/PKCS5Padding Implicit|BCFIPS                                                          |passed|
|SHA512_256Secure             |BCFIPS                                                          |passed|
|SHA512Secure                 |BCFIPS                                                          |passed|
|SHA256Secure                 |BCFIPS                                                          |passed|
|SHA512_256                   |BCFIPS                                                          |passed|
|SHA512                       |BCFIPS                                                          |passed|
|SHA256                       |BCFIPS                                                          |passed|
|SHA1                         |BCFIPS                                                          |passed|
|MD5                          |BCFIPS                                                          |passed|
|STRONG_AES256                |BCFIPS                                                          |passed|
|PLAIN_AES256                 |BCFIPS                                                          |passed|
|PLAIN_AES128                 |BCFIPS                                                          |passed|
|SSL Context                  |BCJSSE                                                          |passed|
|SecureRandom                 |BCFIPS                                                          |passed|

(23 rows)
 ```




[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/17_user_IAM_custom_authenticator.md)


