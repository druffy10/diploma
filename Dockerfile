FROM node:erbium-alpine3.12
WORKDIR /app
COPY ./gate-simulator /app
RUN npm install
EXPOSE 9999
CMD ["npm", "start"]