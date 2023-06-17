# Use the official Node.js image as the base image
FROM node:alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY . .

# Install dependencies
RUN npm install

# Copy the entire project to the container
COPY . .

# Build the Angular app
RUN npm run build

# Expose the port on which the Angular app will run
EXPOSE 4200

# Set the entry point command to run the Angular app
CMD ["npm", "run", "start"]
