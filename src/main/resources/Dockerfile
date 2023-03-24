#apiVersion: apps/v1
#kind: Deployment
#metadata:
#name: point-of-sale-deployment
#labels:
#app: point-of-sale
#spec:
#replicas: 1
#selector:
#matchLabels:
#app: point-of-sale
#template:
#metadata:
#labels:
#app: point-of-sale
#spec:
#containers:
#- name: point-of-sale
#image: <docker-registry-url>/point-of-sale:latest
#ports:
#- containerPort: 8080
#env:
#- name: SPRING_PROFILES_ACTIVE
#value: production