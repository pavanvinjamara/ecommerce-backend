//run below query for all tables in db:
show create table table_name;

-- 1. USERS (identity only)
CREATE TABLE users(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    phone VARCHAR(20),

    is_active BOOLEAN DEFAULT TRUE,
    is_email_verified BOOLEAN DEFAULT FALSE,
    is_phone_verified BOOLEAN DEFAULT FALSE,

    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_at TIMESTAMP,

    last_login_at TIMESTAMP,
    failed_login_attempts INT DEFAULT 0,
    locked_until TIMESTAMP,

    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

--  . USER PROFILE
CREATE TABLE user_profiles(
    user_id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    avatar_url TEXT,
    dob DATE,
    gender VARCHAR(10)
)

-- . ADDRESSES
CREATE TABLE addresses(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,

    line1 TEXT,
    line2 TEXT,
    city VARCHAR(20),
    state VARCHAR(20),
    pincode VARCHAR(20),

    is_default BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT now()
)

--  . CATEGORIES
CREATE TABLE categories(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(20),
    parent_id UUID REFERENCES categories(id),
)

-- . PRODUCTS
CREATE TABLE products(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    name VARCHAR(255),
    description TEXT,
    category_id UUID REFERENCES categories(id),
    created_by UUID REFERENCES users(id),

    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT now()
)

-- . PRODUCT VARIANTS
CREATE TABLE product_variants(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    product_id UUID REFERENCES  products(id) ON DELETE CASCADE,
    price NUMERIC(10, 2),
    stock INT,

    sku VARCHAR(100) UNIQUE, -- sku means Stock Keeping Unit

    attributes JSONB, -- size, color etc.

    created_at TIMESTAMP DEFAULT now()
)

-- . PRODUCT IMAGES
CREATE TABLE product_images(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_id UUID REFERENCES products(id) ON DELETE CASCADE ,

    image_url TEXT,
    is_primary BOOLEAN DEFAULT FALSE
)

-- . CART
CREATE TABLE cart_items(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) on DELETE  CASCADE,
    variant_id UUID REFERENCES product_variants(id),

    quantity INT,
    created_at TIMESTAMP DEFAULT now()
)

-- . ORDERS
CREATE TABLE orders(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id UUID REFERENCES users(id),
    address_id UUID REFERENCES addresses(id),

    status VARCHAR(20), -- pending, shipped, delivered
    payment_status VARCHAR(50),
    total_amount NUMERIC(10, 2),
    created_at TIMESTAMP DEFAULT now()
)

-- . ORDER ITEMS
CREATE TABLE order_items(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    order_id UUID REFERENCES  orders(id) ON DELETE CASCADE,
    variant_id UUID REFERENCES product_variants(id),

    quantity INT,
    price_at_purchase NUMERIC(10, 2)
)

-- . PAYMENTS
CREATE TABLE payments(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    order_id UUID REFERENCES  orders(id),
    method VARCHAR(50), -- UPI, card, COD
    status VARCHAR(50),

    transaction_id VARCHAR(255),

    created_at TIMESTAMP DEFAULT now()
)

-- . REVIEWS
CREATE TABLE reviews (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id REFERENCES users(id),
    product_id REFERENCES products(id),

    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment VARCHAR(255),

    created_at TIMESTAMP DEFAULT now()
)
