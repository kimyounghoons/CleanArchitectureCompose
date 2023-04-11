# 클린 아키텍쳐 컴포즈 프로젝트

# Module Dependency

## Modules
```mermaid
graph TD;
    App-->PopularMovies_Presentation;
    App-->MovieSearch_Presentation;
    App-->NowPlaying_Presentation;
    App-->Detail_Presentation;
    App-->Navigator;
    PopularMovies_Presentation-->Navigator;
    MovieSearch_Presentation-->Navigator;
    NowPlaying_Presentation-->Navigator;
    Detail_Presentation-->Navigator;
```

## Feature Module
```mermaid
graph TD;
    Data & Presentation --> Domain;
    Presentation --> Navigator;
```