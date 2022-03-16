<div id="top"></div>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/IKalonji/FairOSTooling">
    <img src="images/SolAfrique.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">FairOS Java-SDK</h3>

  <p align="center">
    FairOS implementation in Java to easily work with you DFS Server.
    <br />
    <a href="https://github.com/IKalonji/FairOSTooling/blob/main/README.md"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <!-- <a href="https://www.youtube.com/watch?v=T4ouxpaDafk">View Demo</a>
    · -->
    <a href="https://github.com/IKalonji/FairOSTooling/issues">Report Bug</a>
    ·
    <a href="https://github.com/IKalonji/FairOSTooling/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]]

Swarm decentralized, peer-to-peer storage opens up new opportunities for establishing a self-sovereign cloud. It also brings about a paradigm shift on how to use such storage.Layer 2 solutions on top of Swarm, such as FairOS, make additional features available, leading towards unlocking a fairer data economy.

This Java-SDK provides an simple methods to interact with FairOS

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [FairOS](https://docs.fairos.fairdatasociety.org/)
* [Java](https://www.java.com/)
* [Maven](https://maven.apache.org/)
* [WSL2](https://docs.microsoft.com/en-us/windows/wsl/install)

<p align="right">(<a href="#top">back to top</a>)</p>

### Notice

1. This project is not audited and should not be used in a production environment.
2. The project was build on Windows and has not been tested on any Linux distro, but it should run since the tools used are cross platform. 

<!-- GETTING STARTED -->
## Getting Started

Follow the steps below on how to run the project.

### Prerequisites

Please install the below required software in order to run the project. (Follow link for installation guide)

* [DFS-server](https://nodejs.org/about/releases)

* [JDK-11](https://www.oracle.com/java/technologies/downloads/)

* [Maven](https://maven.apache.org/download.cgi)

* [WSL2](https://docs.microsoft.com/en-us/windows/wsl/install)


### Installation

#### NOTE: Please the package has not been audited and fully tested as such it cannot be uploaded to the Maven repository.  

1. Clone the repo
   ```sh
   git clone https://github.com/IKalonji/FairOSTooling.git
   ```
2. Compile
   ```sh
   mvn clean install -U dependency:copy-dependencies
   ```
3. Once compiled, copy the JAR file in the target folder to the /lib folder of your project
   ```sh
   FairOS-Java_SDK.jar --> ./lib
   ```
5. Install Maven dependency and .jar dependency to POM.xml:
  ```sh
  $ mvn install:install-file –Dfile=C:/dev/app.jar -DgroupId=org.javasdk.fairos -DartifactId=FairOS-Java-SDK -Dversion=1.0-SNAPSHOT
  ```

  ```sh
  <dependency>
    <groupId>org.javasdk.fairos</groupId>
    <artifactId>FairOS-Java-SDK</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
  ```
6. Import and instantiate classes to call the required methods, Example:
  ```sh
    User user = new User();
    HashMap signedIn = user.signupUser("username", "strongpassword", "mnemonic")
  ```

  ```sh
    Pod pod = new Pod("cookie");
    HashMap newpod = pop.podNew("podName", "strongpassword")
  ```

  ```sh
    FileSystem fs = new FileSystem("cookie");
    HashMap newDir = fs.fsMakeDirectory("podName", "dirPath")
  ```

  ```sh
    KeyValueDB kvDB = new KeyValueDB("cookie");
    HashMap newKvStore = kvDB.newTable("podName", "tablename", "indexType")
  ```

  ```sh
    DocumentDB docDB = new DocumentDB("cookie");
    HashMap newDocDB = DocumentDB.createDB("podName", "tablename", "DocFieldIndex")
  ```


<p align="right">(<a href="#top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage

See [FairOS](https://docs.fairos.fairdatasociety.org/api/#tag/User) for full list of available requests.

_Please refer to the [Documentation](https://github.com/IKalonji/FairOSTooling/blob/main/README.md)_

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- ROADMAP -->
## Roadmap

- [ ] Add integration tests
- [ ] Refactor methods
- [ ] Implement Singleton pattern
- [ ] Support for Websockets

See the [open issues](https://github.com/IKalonji/FairOSTooling/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Issa Kalonji - [@ISSDawg](https://twitter.com/ISSDawg) - ikalonji@student.wethinkcode.co.za

Project Link: [https://github.com/IKalonji/FairOSTooling](https://github.com/IKalonji/FairOSTooling)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [FairOS](https://docs.fairos.fairdatasociety.org/docs/fairOS-dfs/api-reference/)
* [Gitcoin](https://gitcoin.co/issue/fairdatasociety/wam/16/100027826)
* [WAM](https://www.wearemillions.online/)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/IKalonji/SolAfrique.svg?style=for-the-badge
[contributors-url]: https://github.com/IKalonji/FairOSTooling/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/IKalonji/SolAfrique.svg?style=for-the-badge
[forks-url]: https://github.com/IKalonji/FairOSTooling/network/members
[stars-shield]: https://img.shields.io/github/stars/IKalonji/SolAfrique.svg?style=for-the-badge
[stars-url]: https://github.com/IKalonji/FairOSTooling/stargazers
[issues-shield]: https://img.shields.io/github/issues/IKalonji/SolAfrique.svg?style=for-the-badge
[issues-url]: https://github.com/IKalonji/FairOSTooling/issues
[license-shield]: https://img.shields.io/github/license/IKalonji/SolAfrique.svg?style=for-the-badge
[license-url]: https://github.com/IKalonji/FairOSTooling/blob/main/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/issa-kalonji-b301851ba/
[product-screenshot]: images/screenshot1.png
