import os
from PIL import Image
from PIL.ImageColor import getcolor, getrgb
from PIL.ImageOps import grayscale


modid = "dyenamics"
blockFolder = f"resources/assets/{modid}/textures/block"
itemFolder = f"resources/assets/{modid}/textures/item"
entityFolder = f"resources/assets/{modid}/textures/entity"

colors = {
    "peach": "#BF9873", 
    "aquamarine": "#2C7D7F", 
    "fluorescent": "#EEE9B6", 
    "mint": "#89e8b8", 
    "maroon": "#910000", 
    "bubblegum": "#f771c6", 
    "lavender": "#DD99FF", 
    "persimmon": "#d24119", 
    "cherenkov": "#0198CF",
    "amber": "#D37319",
    "honey": "#ffb446",
    "ultramarine": "#5649ff",
    "spring_green": "#c3ff99",
    "rose": "#ff2865",
    "navy": "#1c2871",
    "icy_blue": "#8FEAFF",
    "wine": "#721E5B",
    "conifer": "#BADA55"
}

blocks = ["shulker_box", "terracotta", "concrete", "concrete_powder", "wool", "stained_glass", "stained_glass_pane_top", "candle"]
items = ["candle"]
entities = ["bed", "shulker", "swag"]
baseBlockTextures = {}
baseItemTextures = {}
baseEntityTextures = {}
for block in blocks:
    baseBlockTextures[block] = Image.open(f"textures/block/{block}.png")
for item in items:
    baseItemTextures[item] = Image.open(f"textures/item/{item}.png")
for entity in entities:
    baseEntityTextures[entity] = Image.open(f"textures/entity/{entity}.png")

def colorImage(image, color='#ffffff'):
    image.load()
    split = image.split()
    
    mult = tuple(map(lambda i: i/255, getrgb(color)))
    for i in range(3):
        split[i].paste(split[i].point(lambda band: band * mult[i]))
    
    return Image.merge(image.mode, split)

def overlayImage(image, image2):
    image.load()
    image.paste(image2, (0,0), mask = image2)
    return image

#File Layout
def genFolders():
    os.makedirs(blockFolder, exist_ok=True)
    os.makedirs(itemFolder, exist_ok=True)
    os.makedirs(entityFolder, exist_ok=True)

#Textures
def genTextures():
    for color in colors:
        for texture in baseBlockTextures:
            if not (color in {"peach", "aquamarine", "fluorescent"} and texture in {"terracotta", "concrete", "concrete_powder", "wool"}):
                path = f"{blockFolder}/{color}_{texture}.png"
                colorImage(baseBlockTextures[texture], colors[color]).save(path)
        for texture in baseItemTextures:
            path = f"{itemFolder}/{color}_{texture}.png"
            overlayImage(colorImage(baseItemTextures[texture], colors[color]), Image.open(f"textures/item/{texture}_overlay.png")).save(path)
        for texture in baseEntityTextures:
            path = f"{entityFolder}/{texture}/{color}.png"
            overlayImage(colorImage(baseEntityTextures[texture], colors[color]), Image.open(f"textures/entity/{texture}_overlay.png")).save(path)

genFolders()
genTextures()