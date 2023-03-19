# docker-Java-kubernetes-project

INSTALL MINIKUBE - https://minikube.sigs.k8s.io/docs/start/

Deploying Java Applications with Docker and Kubernetes

1) Build each project ->> mvn clean install -DskipTests

2) Create docker hub account

3) Build the image in local -> docker build -t praveensingam1994/stockmanager:latest .

docker build -t praveensingam1994/productcatalogue:latest .

docker build -t praveensingam1994/stockmanager:latest .

4) Push the image to your docker hub -> docker push praveensingam1994/shopfront:latest 

docker push praveensingam1994/productcatalogue:latest

docker push praveensingam1994/shopfront:latest

5) Go to kubernetes folder and create the pods -> 

kubectl apply -f shopfront-service.yaml

kubectl apply -f productcatalogue-service.yaml

kubectl apply -f stockmanager-service.yaml

6) minikube service servicename  -> 

minikube service shopfront
minikube service productcatalogue
minikube service stockmanager

7) Hit the url in browser -> 

ORDER TO BUILD AND DEPLOY 

shopfront -> productcatalogue -> stockmanager

Endpoint for product --> /products
Endpoint for stock --> /stocks



