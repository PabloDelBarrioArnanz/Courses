// @ts-check
import { test, expect } from '@playwright/test'

const LOCALHOST_URL = 'http://localhost:4000'
const CAT_ENDPOINT_IMAGE_PREFIX = `https://cataas.com`

test('app shows random fact and image', async ({ page }) => {
  await page.goto(LOCALHOST_URL)

  const text = page.getByRole('paragraph')
  const image = page.getByRole('img')

  const textContent = await text.textContent()
  const imageSrc = await image.getAttribute('src')

  expect(textContent?.length).toBeGreaterThan(0)
  expect(imageSrc?.startsWith(CAT_ENDPOINT_IMAGE_PREFIX)).toBeTruthy()
})

test('app change fact and image when click on button', async ({ page }) => {
  await page.goto(LOCALHOST_URL)

  const text = page.getByRole('paragraph')
  const image = page.getByRole('img')
  const button = page.getByRole('button')

  const textContent = await text.textContent()
  const imageSrc = await image.getAttribute('src')
  
  await button.click()
  await page.waitForTimeout(3000)

  const newText = page.getByRole('paragraph')
  const newImage = page.getByRole('img')

  const newTextContent = await newText.textContent()
  const newImageSrc = await newImage.getAttribute('src')

  expect(newTextContent !== textContent).toBeTruthy()
  expect(newImageSrc !== imageSrc).toBeTruthy()
})

