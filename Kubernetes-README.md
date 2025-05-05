
# Kubernetes Deployment Guide for Spring Boot Microservices

This guide explains how to deploy `user-service`, `order-service`, and `inventory-service` Spring Boot applications to a Kubernetes cluster using Helm charts.

---

## 📁 Project Structure

Each service has its own Helm chart:

```
helm-charts/
├── user-service-0.1.0.tgz
├── order-service-0.1.0.tgz
└── inventory-service-0.1.0.tgz
```

---

## ⚙️ Prerequisites

- Docker image for each service must be pushed to Docker Hub (or another registry).
- Helm must be installed on the deployment machine.
- Kubernetes cluster access must be properly configured (`kubectl` and `helm` work).

---

## 🚀 Deployment Steps

### 1. Package the Helm Charts (if not already)

```bash
helm package charts/user-service
helm package charts/order-service
helm package charts/inventory-service
```

---

### 2. Transfer Charts to Target Machine

```bash
scp user-service-0.1.0.tgz user@<TARGET_IP>:~/helm-charts/
scp order-service-0.1.0.tgz user@<TARGET_IP>:~/helm-charts/
scp inventory-service-0.1.0.tgz user@<TARGET_IP>:~/helm-charts/
```

---

### 3. SSH into Target Machine

```bash
ssh user@<TARGET_IP>
cd ~/helm-charts
```

---

### 4. Install the Services Using Helm

Create namespace if not exists:

```bash
kubectl create namespace my-database
```

Install services:

```bash
helm install user-service ./user-service-0.1.0.tgz -n my-database
helm install order-service ./order-service-0.1.0.tgz -n my-database
helm install inventory-service ./inventory-service-0.1.0.tgz -n my-database
```

---

### 5. Verify Deployment

```bash
kubectl get all -n my-database
```

---

## 🔄 Upgrade Service (if Chart or Image is Updated)

```bash
helm upgrade user-service ./user-service-0.1.0.tgz -n my-database
```

Repeat for others as needed.

---

## 🧹 Delete a Deployment

```bash
helm uninstall user-service -n my-database
```

---

## 📌 Notes

- Each chart must include a valid `_helpers.tpl` file defining `fullname` template.
- Docker image reference must be correct in each chart’s `values.yaml`.
