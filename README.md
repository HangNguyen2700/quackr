# quackr

## Build images

- Build backend image
  ```bash
  cd quackr-backend
  docker build -t backend-image .
  ```
- Build frontend image
  ```bash
  cd quackr-angular
  docker build -t frontend-image .
  ```

## Run project

```
docker-compose up
```
