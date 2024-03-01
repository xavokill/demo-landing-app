FROM node:20.11.0 as builder

WORKDIR /sources

COPY . .

RUN npm install

RUN npm run build
 

FROM nginx:latest as final

COPY --from=builder /sources/dist/browser/ /usr/share/nginx/html

EXPOSE 80


#docker build --tag "academiafullstackdemo:landing-v1" .
#docker run -p 8081:80 --name "demo-landings-dev" academiafullstackdemo:landig-v1