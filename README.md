# Base Android MVI
Base Android project with MVI, Clean Architecture, Coroutines and Hilt as Dependency Injection.

## Project Configuration and Tools
### KtLint
To start KtLint check run:<br>
`./gradlew ktlintCheck` - to checks all SourceSets and project Kotlin script files.<br>
`./gradlew ktlintFormat` - tries to format according to the code style all SourceSets Kotlin files and project Kotlin script files.

**Helper KtLint tasks**<br>
`./gradlew addKtlintCheckGitPreCommitHook` - adds Git pre-commit hook, that runs ktlint check over staged files.