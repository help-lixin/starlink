---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ${DEPLOYMENT_NAME}
  namespace: ${NAMESPACE}
spec:
<#if  lables?? && (lables?size > 0)  >
  selector:
    matchLabels:
      <#list lables?keys as key >
      ${key}: ${lables[key]}
      </#list>
</#if>
  template:
  <#if  lables?? && (lables?size > 0)  >
    metadata:
      labels:
        <#list lables?keys as key >
        ${key}: ${lables[key]}
        </#list>
  </#if>
    spec:
      imagePullSecrets:
        - name: ${IMAGE_PULL_SECRET_NAME}
      containers: 
        - image: ${IMAGE}
          name: ${projectName}
          ports:
            - containerPort: ${PORT}
          <#if  envs?? && (envs?size > 0)  >
          env:
          <#list envs as env >
            - name: ${env.name}
              value: ${env.value}
          </#list>
          </#if>