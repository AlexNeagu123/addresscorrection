# Advanced Programming 2023. Final Project.

- :adult: **Alexandru Neagu** from **2B2** (transferred from **2A6**).
- :adult: **Cristian Fiodorov** from **2A4** (teacher **Gabor Silviu**)

This repository contains the code for

## **Address Correction API** project ##

:heavy_check_mark: Supports 10+ countries (70 on **main** branch and 25 on **deployment** branch).

:heavy_check_mark: Supports addresses written in multiple languages.

:heavy_check_mark: Implemented the input address **normalization**.
   - _:file_folder: src/main/java/com/pa/utility/AddressNormalizer.java_

:heavy_check_mark: Made 100 **Unit Tests** in order to test the correction accuracy (%) (a lot of cases treated including **crossfield** and **elimination**)
  - _:file_folder: src/test/java/com/pa/service/AddressCorrectionServiceTest.java_

:heavy_check_mark: Made tests for **latency** using **Apache JMeter**

:heavy_check_mark: Stored all the _countries_, _cities_ and _states_ as nodes in a graph and the relationships between them as edges.

:heavy_check_mark: The resulted graph is a **forest** that we store in memory.

:heavy_check_mark: Used **Google Guava**'s Multimap in order to map the ascii names to nodes in the forest (this step speeds up the searching process a lot). 

:heavy_check_mark: Implemented a service that extracts all the solution candidates based on the normalized tokens.

:heavy_check_mark: Implemented an algorithm that assigns a score to each solution candidate .

:heavy_check_mark: Successfully deployed the application in cloud using **Microsoft Azure** [the code executed in deployment is on deployment branch].

:heavy_check_mark: Used **Github Actions** to add **Continuous Integration** on the deployment branch.
