

# 🚀 Proyecto Alarma

Este es un proyecto desarrollado en **Angular**, diseñado para ejecutarse en un entorno local. A continuación, encontrarás las instrucciones para instalar, configurar y ejecutar la aplicación.
# Alarma

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 15.2.6.
---

## 📋 **Requisitos Previos**

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Node.js](https://nodejs.org/) (Versión recomendada: **LTS**)
- [Angular CLI](https://angular.io/cli) (Interfaz de Línea de Comandos de Angular)
- [Git](https://git-scm.com/) (Opcional, pero recomendado)

Puedes verificar las versiones instaladas ejecutando:

```sh
node -v
npm -v
ng version
```

Si Angular CLI no está instalado, puedes hacerlo con:

```sh
npm install -g @angular/cli
```

---

## 👅 **Instalación del Proyecto**

1️⃣ **Clona el repositorio**:

```sh
git clone https://github.com/nicoGarciaValde/alarma-ux/tree/Creacion_pomodoro/alarma
```

2️⃣ **Accede al directorio del proyecto**:

```sh
cd repo-angular
```

3️⃣ **Instala las dependencias**:

```sh
npm install
```

---

## ▶️ **Ejecutar el Proyecto en Desarrollo**

Para iniciar la aplicación en **modo desarrollo**, usa:

```sh
ng serve
```

Esto iniciará un servidor local en:

```sh
http://localhost:4200/
```

Si quieres abrirlo automáticamente en el navegador:

```sh
ng serve --open
```

---

## 💁️ **Estructura del Proyecto**

```
repo-angular/
│── src/                  # Código fuente del proyecto
│   ├── app/              # Componentes y servicios principales
│   ├──├── feature/       # Componentes desarrollados  
│   ├──├── shared/        # COmponentes compartidos

│   ├── assets/           # Recursos estáticos (imágenes, estilos, etc.)
│   ├── environments/     # Configuración de entornos
│   ├── main.ts           # Punto de entrada de la aplicación
│   └── index.html        # Archivo HTML principal
│
├── angular.json          # Configuración de Angular
├── package.json          # Dependencias del proyecto
├── README.md             # Documentación del proyecto
├── tsconfig.json         # Configuración de TypeScript
└── .gitignore            # Archivos a ignorar en Git
```

---

## 📏 **Notas Adicionales**
- Puedes cambiar el puerto por defecto con:

  ```sh
  ng serve --port=4300
  ```

- Si tienes errores en dependencias, intenta:

  ```sh
  rm -rf node_modules package-lock.json
  npm install
  ```

- Si el servidor no responde, asegúrate de que no haya otro proceso en el puerto **4200**:

  ```sh
  npx kill-port 4200
  ```

---

## 📞 **Soporte**
Si tienes problemas al ejecutar el proyecto, puedes escribirnos a 
- "nico.ing.mec.2@gmail.com", "jc.hernandez23@uniandes.edu.co", "ne.garciav1@uniandes.edu.co"
- Si es necesario whatsapp "+57 3168662046" ó tambien SLACK, estaremos pendientes
