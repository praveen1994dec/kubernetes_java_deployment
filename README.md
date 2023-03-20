# docker-Java-kubernetes-project


INSTALL MINIKUBE 
***********************************************************

KUBECTL
curl -o kubectl https://amazon-eks.s3.us-west-2.amazonaws.com/1.20.4/2021-04-12/bin/linux/amd64/kubectl
chmod +x ./kubectl
mkdir -p $HOME/bin
cp ./kubectl $HOME/bin/kubectl
export PATH=$HOME/bin:$PATH
echo 'export PATH=$HOME/bin:$PATH' >> ~/.bashrc
source $HOME/.bashrc
kubectl version --short --client

DOCKER
yum install docker -y
systemctl  start docker
systemctl enable docker

https://minikube.sigs.k8s.io/docs/start/
***********************************************************



INSTALL EKS SETUP
#############################################################
Step1: Take EC2 Instance with t2.MEDIUM instance type
Step2: Create IAM Role with Admin policy for eks-cluster and attach to ec2-instance
Step3: Install kubectl

curl -o kubectl https://amazon-eks.s3-us-west-2.amazonaws.com/1.14.6/2019-08-22/bin/linux/amd64/kubectl
chmod +x ./kubectl
mkdir -p $HOME/bin
cp ./kubectl $HOME/bin/kubectl
export PATH=$HOME/bin:$PATH
echo 'export PATH=$HOME/bin:$PATH' >> ~/.bashrc
source $HOME/.bashrc
kubectl version --short --client

Step4: Install eksctl:
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/bin
eksctl version

Step5: MASTER Cluster creation:
eksctl create cluster --name=eksdemo \
                  --region=us-west-1 \
                  --zones=us-west-1b,us-west-1c \
                  --without-nodegroup 

Step6: Add Iam-Oidc-Providers:
eksctl utils associate-iam-oidc-provider \
    --region us-west-1 \
    --cluster eksdemo \
    --approve 

Allowing the service to connect with EKS


Step7: WORKER NODE Create node-group:
eksctl create nodegroup --cluster=eksdemo \
                   --region=us-west-1 \
                   --name=eksdemo-ng-public \
                   --node-type=t2.medium \
                   --nodes=2 \
                   --nodes-min=2 \
                   --nodes-max=4 \
                   --node-volume-size=10 \
                   --ssh-access \
                   --ssh-public-key=Praveen-test \
                   --managed \
                   --asg-access \
                   --external-dns-access \
                   --full-ecr-access \
                   --appmesh-access \
                   --alb-ingress-access	


 
//eksctl delete nodegroup --cluster=eksdemo --region=us-east-1 --name=eksdemo-ng-public



//eksctl delete cluster --name=eksdemo    --region=us-west-1	




#############################################################


HANDSON


Deploying Java Applications with Docker and Kubernetes

1) Build each project ->> mvn clean install -DskipTests

2) Create docker hub account

3) Build the image in local -> docker build -t praveensingam1994/shopfront:latest .

docker build -t praveensingam1994/productcatalogue:latest .

docker build -t praveensingam1994/stockmanager:latest .

4) Push the image to your docker hub -> docker push praveensingam1994/shopfront:latest 

docker push praveensingam1994/productcatalogue:latest

docker push praveensingam1994/stockmanager:latest

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



