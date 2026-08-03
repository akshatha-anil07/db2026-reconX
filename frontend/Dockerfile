# ============================================================================
# TICKET-ADV147 — React multi-stage build (npm -> nginx)
#
# TODO: Implement a two-stage Docker build:
#   Stage 1 (build): Use node:22-alpine to install deps and run `npm run build`.
#   Stage 2 (runtime): Copy dist/ into nginx:1.27-alpine html root.
#   - Copy nginx.conf for SPA fallback and /api/ proxy rewrite.
#   - EXPOSE port 80.
# ============================================================================

FROM node:22-alpine
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
EXPOSE 5173
CMD ["npm", "run", "preview"]
