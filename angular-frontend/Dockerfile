#Stage 1
FROM node:8.11.2-alpine as node
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
RUN npm install ngx-file-drop
RUN npm install angular-bootstrap-md --save
RUN npm install rxjs-compat
RUN npm install api-ai-javascript
RUN npm install stompjs
RUN npm install sockjs
RUN npm install jquery
RUN npm install browser-sync --save-dev
RUN npm i net -S
RUN npm install --save d3
RUN npm install --save-dev @types/d3
COPY . .
RUN npm run build --prod
# Stage 2
FROM node
RUN npm install http-server -g
#CMD ["http-server","-p","8079", "dist/knowledgeHubFrontEnd","-S","--cert","ssl/cert.pem","--key","ssl/key.pem"]
CMD ["http-server","-p","8079", "dist/knowledgeHubFrontEnd"]
