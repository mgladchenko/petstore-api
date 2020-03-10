**Setup on OS X:**
1. Install Homebrew package manager if not installed (https://brew.sh/)
2. Install JDK 11 via Homebrew  
`brew cask uninstall java`  
`brew tap caskroom/versions`  
`brew cask install java11`  
3. Verify that JDK 11 was installed
`java -version`
4. Install Maven via Homebrew
`brew install maven`
5. Verify that Maven was installed
`mvn -version`